package com.cubebaby

import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager

/**
 * CubeBaby USB Scanner
 *
 * Responsável por localizar a Cube Baby conectada via OTG.
 *
 * Hardware identificado:
 * VID = 0x301A
 * PID = 0x5555
 */
class UsbScanner(private val context: Context) {

    companion object {
        const val CUBE_VENDOR_ID = 0x301A
        const val CUBE_PRODUCT_ID = 0x5555
    }

    private val usbManager =
        context.getSystemService(Context.USB_SERVICE) as UsbManager

    /**
     * Procura a Cube Baby entre todos os dispositivos USB.
     */
    fun findCubeBaby(): UsbDevice? {

        val devices = usbManager.deviceList.values

        for (device in devices) {

            if (device.vendorId == CUBE_VENDOR_ID &&
                device.productId == CUBE_PRODUCT_ID
            ) {
                return device
            }
        }

        return null
    }

    /**
     * Lista todos os dispositivos USB.
     *
     * Útil para debug.
     */
    fun dumpDevices(): String {

        val sb = StringBuilder()

        val devices = usbManager.deviceList.values

        if (devices.isEmpty()) {
            sb.append("No USB devices found\n")
        }

        devices.forEach {

            sb.append("---------------------------------\n")
            sb.append("Device: ${it.deviceName}\n")
            sb.append("Vendor : ${it.vendorId}\n")
            sb.append("Product: ${it.productId}\n")
            sb.append("Class  : ${it.deviceClass}\n")
            sb.append("Subclass: ${it.deviceSubclass}\n")
            sb.append("Protocol: ${it.deviceProtocol}\n")
            sb.append("Interfaces: ${it.interfaceCount}\n")

            for (i in 0 until it.interfaceCount) {

                val intf = it.getInterface(i)

                sb.append("  Interface $i\n")
                sb.append("      Class=${intf.interfaceClass}\n")
                sb.append("      Subclass=${intf.interfaceSubclass}\n")
                sb.append("      Protocol=${intf.interfaceProtocol}\n")
                sb.append("      Endpoints=${intf.endpointCount}\n")
            }
        }

        return sb.toString()
    }

}
