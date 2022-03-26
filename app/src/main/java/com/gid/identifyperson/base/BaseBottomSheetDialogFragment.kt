package com.gid.identifyperson.base

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @Author: Khimheang DARA
 * @Date: 10/11/21
 */
abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment(), BaseDialogView {
    private var baseActivity: BaseActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getLayout()
    }

    /**
     * Return view to setContentView onCreateView
     * @return View
     */
    protected abstract fun getLayout(): View

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.baseActivity = context
            context.onFragmentAttached()
        }
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    fun getBaseActivity(): BaseActivity? {
        return baseActivity
    }

    override fun hideKeyboard() {
        baseActivity?.hideKeyboard()
    }

    override fun hideLoading() {
        baseActivity?.hideLoading()
    }

    override fun showLoading() {
        baseActivity?.showLoading()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun isNetworkConnected(): Boolean {
        return baseActivity?.isNetworkConnected() == true
    }

    override fun showBackArrow() {
        val supportActionBar: ActionBar? = baseActivity?.supportActionBar
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar.setDisplayShowHomeEnabled(true)
        }
    }

    override fun dismissDialog(tag: String?) {
        dismiss()
        getBaseActivity()?.onFragmentDetached(tag)
    }

    /**
     * Request permission for fragment
     * @param permissions Array<String?>?
     * @param requestCode Int
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String?>?, requestCode: Int) {
        baseActivity?.requestPermissionsSafely(permissions, requestCode)
    }

    /**
     * Check permission for fragment
     * @param permission String?
     * @return Boolean?
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String?): Boolean? {
        return baseActivity?.hasPermission(permission)
    }

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String?)
    }
}
