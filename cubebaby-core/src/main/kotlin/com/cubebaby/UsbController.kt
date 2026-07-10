package com.cubebaby

import android.content.Context
import android.hardware.usb.UsbManager

class UsbController(
    private val context: Context,
    private val logger: (String) -> Unit
) {

    private val usbManager =
        context.getSystemService(Context.USB_SERVICE) as UsbManager

    fun connect() {

        logger("Scanning USB devices...")

        val scanner = UsbScanner(context)

        val cube = scanner.findCubeBaby()

        if (cube == null) {

            logger("")
            logger("Cube Baby NOT FOUND")
            return

        }

        logger("")
        logger("Cube Baby FOUND")
        logger("Vendor : ${cube.vendorId}")
        logger("Product: ${cube.productId}")
        logger("Interfaces: ${cube.interfaceCount}")

        val permission =
            UsbPermissionManager(context, usbManager)

        if (!permission.hasPermission(cube)) {

            logger("")
            logger("USB permission required.")
            permission.requestPermission(cube)
            return

        }

        logger("")
        logger("USB permission granted.")

        val connection =
            UsbConnectionManager(usbManager)

        logger(connection.connect(cube))

    }

}
