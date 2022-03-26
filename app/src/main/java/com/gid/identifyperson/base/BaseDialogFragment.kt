package com.gid.identifyperson.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 * @Author: Sothy Sin
 * @Date: 1/9/21
 */
abstract class BaseDialogFragment : DialogFragment(), BaseDialogView {

    private var mActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.mActivity = context
            context.onFragmentAttached()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun show(fragmentManager: FragmentManager, tag: String?) {
        val transaction = fragmentManager.beginTransaction()
        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }

    override fun dismissDialog(tag: String?) {
        dismiss()
        getBaseActivity()?.onFragmentDetached(tag)
    }

    override fun hideKeyboard() {
        mActivity?.hideKeyboard()
    }

    override fun hideLoading() {
        mActivity?.hideLoading()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun isNetworkConnected(): Boolean {
        return mActivity?.isNetworkConnected() == true
    }

    override fun showLoading() {
        mActivity?.showLoading()
    }

    /**
     * Get base activity
     * @return BaseActivity?
     */
    open fun getBaseActivity(): BaseActivity? {
        return mActivity
    }
}