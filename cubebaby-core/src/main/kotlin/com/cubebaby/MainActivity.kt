package com.cubebaby

import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var output: TextView
    private lateinit var usbReceiver: UsbPermissionReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        output = TextView(this).apply {
            textSize = 14f
            setPadding(24, 24, 24, 24)
        }

        setContentView(output)

        log("CubeBaby Tools")
        log("========================")
        log("Version 0.1")
        log("")

        // Receiver que será chamado quando o usuário responder
        // ao pedido de permissão USB.
        usbReceiver = UsbPermissionReceiver(

            onGranted = {

                runOnUiThread {

                    log("")
                    log("USB permission GRANTED")
                    log("")

                    UsbController(this, ::log).connect()

                }

            },

            onDenied = {

                runOnUiThread {

                    log("")
                    log("USB permission DENIED")

                }

            }

        )

        registerReceiver(
            usbReceiver,
            IntentFilter(UsbPermissionManager.ACTION_USB_PERMISSION)
        )

        // Inicia o fluxo da aplicação
        UsbController(this, ::log).connect()

    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(usbReceiver)
    }

    private fun log(message: String) {
        output.append(message)
        output.append("\n")
    }

}
