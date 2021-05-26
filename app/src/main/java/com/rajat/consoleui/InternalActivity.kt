package com.rajat.consoleui

import android.content.Context
import android.content.Intent
import android.os.Bundle

private const val TAG = "InternalActivity"

class InternalActivity : BaseActivity() {

    companion object {
        fun createInternalActivityIntent(context: Context): Intent {
            return Intent(context, InternalActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal)

        val consoleLogger = ConsoleLogger(this)

        consoleLogger.d(TAG, "Debug Log")
        consoleLogger.w(TAG, "Warning Log")
        consoleLogger.v(TAG, "Verbose Log")
        consoleLogger.e(TAG, "Error Log")
        consoleLogger.i(TAG, "Info Log")
    }
}
