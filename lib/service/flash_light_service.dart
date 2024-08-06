import 'package:flutter/services.dart';

class FlashLightService {
  static const methodChannel = MethodChannel("flashlights");

  static Future<bool> switchLight(bool turnOn) async {
    try {
      final bool result =
          await methodChannel.invokeMethod("Flash", {'turnOn': turnOn});
      return result;
    } on PlatformException catch (e) {
      print("Qurilmada xatolik berildi: ${e.message}");
      return false;
    }
  }
}
