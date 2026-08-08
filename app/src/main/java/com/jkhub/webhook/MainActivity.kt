package com.jkhub.webhook

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val webhookUrl = findViewById<EditText>(R.id.webhookUrl)
        val signalType = findViewById<EditText>(R.id.signalType)
        val pair = findViewById<EditText>(R.id.pair)
        val entry = findViewById<EditText>(R.id.entry)
        val sl = findViewById<EditText>(R.id.sl)
        val tp1 = findViewById<EditText>(R.id.tp1)
        val tp2 = findViewById<EditText>(R.id.tp2)
        val sendButton = findViewById<Button>(R.id.sendButton)
        val status = findViewById<TextView>(R.id.status)

        sendButton.setOnClickListener {

            val url = webhookUrl.text.toString().trim()
            val type = signalType.text.toString().trim()
            val symbol = pair.text.toString().trim()
            val entryPrice = entry.text.toString().trim()

            if (url.isEmpty() || symbol.isEmpty() || entryPrice.isEmpty()) {
                status.text = "Please fill required fields"
                return@setOnClickListener
            }

            status.text = "Signal ready: $type $symbol"
        }
    }
}                return@setOnClickListener
            }

            status.text = "Signal ready: $type $symbol"
        }
    }
}
