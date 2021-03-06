package com.rajat.consoleui

import android.util.Log

/** Corresponds to different severities of logs. */
enum class LogLevel constructor(val logLevel: Int) {
    VERBOSE(Log.VERBOSE),
    DEBUG(Log.DEBUG),
    INFO(Log.INFO),
    WARNING(Log.WARN),
    ERROR(Log.ERROR)
}
