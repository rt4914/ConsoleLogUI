package com.rajat.consoleui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

private const val TAG = "BaseActivity"

open class BaseActivity : AppCompatActivity() {
    override fun setContentView(resId: Int) {
        val screenRootView = FrameLayout(this)
        screenRootView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )

        val view = this.layoutInflater.inflate(R.layout.activity_base, null)

        val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val consoleLogger = ConsoleLogger(this)

        val screenView: View = inflater.inflate(resId, null)
        val showLogsFab = view.findViewById<ExtendedFloatingActionButton>(R.id.show_all_logs_fab)
        showLogsFab.setOnClickListener {
            consoleLogger.d(TAG, "Show logs button clicked")
            ShowLogDialogFragment
                .newInstance()
                .showNow(this.supportFragmentManager, ShowLogDialogFragment.TAG)
        }
        screenRootView.addView(view)
        screenRootView.addView(screenView)
        super.setContentView(screenRootView)
    }
}
