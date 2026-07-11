package com.cubebaby

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbManager

class UsbPermissionReceiver(
    private val onGranted: () -> Unit,
    private val onDenied: () -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action != UsbPermissionManager.ACTION_USB_PERMISSION) {
            return
        }

        val granted = intent.getBooleanExtra(
            UsbManager.EXTRA_PERMISSION_GRANTED,
            false
        )

        if (granted) {
            onGranted()
        } else {
            onDenied()
        }
    }
}
