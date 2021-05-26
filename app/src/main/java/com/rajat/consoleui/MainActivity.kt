package com.rajat.consoleui

import android.os.Bundle
import android.widget.Button

private const val TAG = "MainActivity"

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val consoleLogger = ConsoleLogger(this)
        val nextActivityButton = findViewById<Button>(R.id.next_activity_button)
        nextActivityButton.setOnClickListener {
            consoleLogger.d(TAG, "Next activity button clicked")
            startActivity(
                InternalActivity.createInternalActivityIntent(this)
            )
        }
    }
}
