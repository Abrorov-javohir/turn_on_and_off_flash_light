import 'package:flutter/material.dart';
import 'package:turn_on_and_off_flash_light/service/flash_light_service.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  bool isFlashlightOn = false; // Chiroqning hozirgi holatini kuzatish uchun

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Home Screen"),
      ),
      body: Center(
        child: SwitchListTile(
          value: isFlashlightOn,
          onChanged: (value) async {
            // Yangi qiymatni isFlashlightOn'ga o'rnatamiz
            isFlashlightOn = await FlashLightService.switchLight(value);
            setState(() {});
          },
          title: const Text("Flash Light"),
        ),
      ),
    );
  }
}
