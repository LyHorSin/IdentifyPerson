package com.gid.identifyperson.base

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

/**
 * @Author: Sothy Sin
 * @Date: 1/9/21
 */
abstract class BaseFragment : Fragment(), BaseView {

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

    override fun onError(resId: Int) {

    }

    override fun onError(message: String?) {

    }

    override fun showMessage(message: String?) {

    }

    override fun showMessage(resId: Int) {

    }

    override fun showBackArrow() {

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

    /**
     * Check permission for activity
     * @param permissions Array<String?>
     * @return Boolean
     */
    fun hasPermission(permissions: Array<String?>): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    permission!!
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return false
            }
        }

        return true
    }

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String?)
    }
}
