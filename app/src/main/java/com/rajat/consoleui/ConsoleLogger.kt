package com.rajat.consoleui

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.Calendar

/** Wrapper class for Android logcat and file logging. All logs in the app should use this class. */
class ConsoleLogger(context: Context) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val logFile = File(context.filesDir, "console_ui.log")

    /** Logs a verbose message with the specified tag.*/
    fun v(tag: String, msg: String) {
        writeLog(LogLevel.VERBOSE, tag, msg)
    }

    /** Logs a verbose message with the specified tag, message and exception.*/
    fun v(tag: String, msg: String, tr: Throwable) {
        writeError(LogLevel.VERBOSE, tag, msg, tr)
    }

    /** Logs a debug message with the specified tag*/
    fun d(tag: String, msg: String) {
        writeLog(LogLevel.DEBUG, tag, msg)
    }

    /** Logs a debug message with the specified tag, message and exception.*/
    fun d(tag: String, msg: String, tr: Throwable) {
        writeError(LogLevel.DEBUG, tag, msg, tr)
    }

    /** Logs a info message with the specified tag.*/
    fun i(tag: String, msg: String) {
        writeLog(LogLevel.INFO, tag, msg)
    }

    /** Logs a info message with the specified tag, message and exception.*/
    fun i(tag: String, msg: String, tr: Throwable) {
        writeError(LogLevel.INFO, tag, msg, tr)
    }

    /** Logs a warn message with the specified tag.*/
    fun w(tag: String, msg: String) {
        writeLog(LogLevel.WARNING, tag, msg)
    }

    /** Logs a warn message with the specified tag, message and exception.*/
    fun w(tag: String, msg: String, tr: Throwable) {
        writeError(LogLevel.WARNING, tag, msg, tr)
    }

    /** Logs a error message with the specified tag.*/
    fun e(tag: String, msg: String) {
        writeLog(LogLevel.ERROR, tag, msg)
    }

    /** Logs a error message with the specified tag, message and exception.*/
    fun e(tag: String, msg: String, tr: Throwable?) {
        writeError(LogLevel.ERROR, tag, msg, tr)
    }

    private fun writeLog(logLevel: LogLevel, tag: String, log: String) {
        writeInternal(logLevel, tag, log)
    }

    private fun writeError(logLevel: LogLevel, tag: String, log: String, tr: Throwable?) {
        writeInternal(logLevel, tag, "$log\n${Log.getStackTraceString(tr)}")
    }

    private fun writeInternal(logLevel: LogLevel, tag: String, fullLog: String) {
        if (BuildConfig.DEBUG) {
            logToFileInBackground("${Calendar.getInstance().time}\t${logLevel.name}/$tag: $fullLog")
        }
        Log.println(logLevel.logLevel, tag, fullLog)
    }

    private fun logToFileInBackground(text: String) {
        scope.launch { writeFileOnInternalStorage(text) }
    }

    private fun writeFileOnInternalStorage(line: String) {
        if (!logFile.exists()) {
            logFile.createNewFile()
        }
        logFile.appendText(line)
        logFile.appendText("\n")
    }

    /** Return all saved logs as a list of strings. */
    fun readLogs(): MutableList<String> {
        val lineList = mutableListOf<String>()
        if (!logFile.exists()) {
            return lineList
        }
        logFile.useLines { lines -> lines.forEach { lineList.add(it) } }
        return lineList
    }

    /**  */
    fun clearLogs() {
        logFile.delete()
    }
}
