package com.gid.identifyperson.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import com.gid.identifyperson.R

/**
 * @Author: Sothy Sin
 * @Date: 1/9/21
 */
object CommonUtils {

    /**
     * Return dialog to view
     * @param context Context?
     * @return Dialog
     */
    fun showLoadingDialog(context: Context?): Dialog {
        val progressDialog = AlertDialog.Builder(context)
        progressDialog.setCancelable(false)
        val inflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val dialogView: View? = inflater?.inflate(R.layout.progress_dialog, null)
        progressDialog.setView(dialogView)
        val dialog: Dialog = progressDialog.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        return dialog
    }

    /**
     * Set time out
     * @param runnable Runnable?
     * @param delay Long
     * @return Any
     */
    fun setTimeout(runnable: Runnable?, delay: Long): Any {
        return TimeoutEvent(runnable!!, delay)
    }

    /**
     * Clear time out
     * @param timeoutEvent Any?
     */
    fun clearTimeout(timeoutEvent: Any?) {
        if (timeoutEvent != null && timeoutEvent is TimeoutEvent) {
            timeoutEvent.cancelTimeout()
        }
    }

    private class TimeoutEvent constructor(task: Runnable, delay: Long) {
        @Volatile
        private var runnable: Runnable?
        fun cancelTimeout() {
            runnable = null
        }

        companion object {
            private val handler: Handler = Handler(Looper.getMainLooper())
        }

        init {
            runnable = task
            handler.postDelayed({
                if (runnable != null) {
                    runnable!!.run()
                }
            }, delay)
        }
    }
}