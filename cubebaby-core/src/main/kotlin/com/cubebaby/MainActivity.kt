package com.cubebaby

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var output: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        output = TextView(this)
        output.textSize = 14f
        output.setPadding(24,24,24,24)

        setContentView(output)

        log("CubeBaby Tools")
        log("====================")
        log("")

        val scanner = UsbScanner(this)

        log("Scanning USB devices...")
        log("")

        log(scanner.dumpDevices())

        val cube = scanner.findCubeBaby()

        if (cube == null) {

            log("")
            log("Cube Baby NOT FOUND")

        } else {

            log("")
            log("Cube Baby FOUND")
            log("Vendor : ${cube.vendorId}")
            log("Product: ${cube.productId}")
            log("Interfaces: ${cube.interfaceCount}")

        }

    }

    private fun log(msg: String) {
        output.append(msg + "\n")
    }

}
