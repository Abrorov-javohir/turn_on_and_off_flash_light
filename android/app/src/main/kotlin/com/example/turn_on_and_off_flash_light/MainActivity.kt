package com.example.turn_on_and_off_flash_light

import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val CHANNEL = "flashlights"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "Flash") {
                val turnOn = call.argument<Boolean>("turnOn") ?: false
                val success = toggleFlashlight(turnOn)
                if (success) {
                    result.success(turnOn)
                } else {
                    result.error("UNAVAILABLE", "Flashlight not available.", null)
                }
            } else {
                result.notImplemented()
            }
        }
    }

    private fun toggleFlashlight(turnOn: Boolean): Boolean {
        val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        return try {
            val cameraId = cameraManager.cameraIdList[0] // Usually the rear camera is the first one
            cameraManager.setTorchMode(cameraId, turnOn)
            true
        } catch (e: CameraAccessException) {
            false
        }
    }
}
