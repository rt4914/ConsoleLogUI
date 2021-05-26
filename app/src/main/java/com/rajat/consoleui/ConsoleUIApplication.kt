package com.rajat.consoleui

import android.app.Application

/** The root [Application] of the ConsoleUI app. */
class ConsoleUIApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        clearAllStoredLogs()
    }

    /** Clear all previously saved logs at the start of application. */
    private fun clearAllStoredLogs(){

        val consoleLogger  = ConsoleLogger(this)
        consoleLogger.clearLogs()
    }
}