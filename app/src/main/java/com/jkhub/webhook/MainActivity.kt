package com.jkhub.webhook

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = TextView(this)

        text.text = """
            JK HUB
            Trading Control Panel

            TradingView → Telegram → APK
        """.trimIndent()

        text.textSize = 24f
        text.setPadding(40, 80, 40, 40)

        setContentView(text)
    }
}
