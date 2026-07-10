package com.cubebaby

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager

class UsbPermissionManager(
    private val context: Context,
    private val usbManager: UsbManager
) {

    companion object {
        const val ACTION_USB_PERMISSION =
            "com.cubebaby.USB_PERMISSION"
    }

    /**
     * Verifica se já existe permissão.
     */
    fun hasPermission(device: UsbDevice): Boolean {
        return usbManager.hasPermission(device)
    }

    /**
     * Solicita permissão ao Android.
     */
    fun requestPermission(device: UsbDevice) {

        val permissionIntent = PendingIntent.getBroadcast(
            context,
            0,
            Intent(ACTION_USB_PERMISSION),
            PendingIntent.FLAG_IMMUTABLE
        )

        usbManager.requestPermission(
            device,
            permissionIntent
        )

    }

}
