package com.cubebaby

import android.hardware.usb.*

class UsbConnectionManager(
    private val usbManager: UsbManager
) {

    fun connect(device: UsbDevice): String {

        val log = StringBuilder()

        log.append("Opening USB connection...\n")

        if (!usbManager.hasPermission(device)) {

            log.append("USB permission NOT granted\n")
            return log.toString()

        }

        val connection = usbManager.openDevice(device)

        if (connection == null) {

            log.append("Unable to open device\n")
            return log.toString()

        }

        log.append("USB connection OPEN\n")
        log.append("\n")

        for (i in 0 until device.interfaceCount) {

            val intf = device.getInterface(i)

            log.append("Interface $i\n")
            log.append("---------------------\n")
            log.append("Class      : ${intf.interfaceClass}\n")
            log.append("Subclass   : ${intf.interfaceSubclass}\n")
            log.append("Protocol   : ${intf.interfaceProtocol}\n")
            log.append("Endpoints  : ${intf.endpointCount}\n")

            if (connection.claimInterface(intf, true)) {

                log.append("Claim: SUCCESS\n")

            } else {

                log.append("Claim: FAILED\n")

            }

            log.append("\n")

            for (e in 0 until intf.endpointCount) {

                val ep = intf.getEndpoint(e)

                log.append("Endpoint $e\n")
                log.append("Address   : ${ep.address}\n")
                log.append("Direction : ${ep.direction}\n")
                log.append("Type      : ${ep.type}\n")
                log.append("MaxPacket : ${ep.maxPacketSize}\n")
                log.append("\n")

            }

        }

        return log.toString()

    }

}
