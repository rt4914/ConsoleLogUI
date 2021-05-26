package com.rajat.consoleui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class ShowLogDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "ShowLogDialogFragment"
        fun newInstance(): ShowLogDialogFragment {
            return ShowLogDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.show_log_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    private fun setupView(view: View) {
        val consoleLogger = ConsoleLogger(this.requireContext())

        val showLogsTextView = view.findViewById<TextView>(R.id.show_logs_text_view)
        if (consoleLogger.readLogs().isEmpty()) {
            showLogsTextView.append("No logs found")
        }
        consoleLogger.readLogs().forEach {
            showLogsTextView.append(it + "\n\n")
        }

        val closeDialog = view.findViewById<ImageView>(R.id.close_image_view)
        closeDialog.setOnClickListener {
            dismissDialog()
        }
    }

    private fun dismissDialog(){
        this.dismiss()
    }

}