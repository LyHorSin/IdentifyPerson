package com.gid.identifyperson.base

import android.annotation.TargetApi
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.gid.identifyperson.utils.CommonUtils
import com.gid.identifyperson.utils.KeyboardUtils
import com.gid.identifyperson.utils.NetworkUtils

/**
 * @Author: Sothy Sin
 * @Date: 1/9/21
 */
abstract class BaseActivity : AppCompatActivity(), BaseView, BaseFragment.Callback {

    private var mProgressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    /**
     * Add layout to activity
     * @return View
     */
    protected abstract fun getLayout(): View

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String?) {

    }

    override fun hideKeyboard() {
        KeyboardUtils.hideSoftInput(this)
    }

    override fun showBackArrow() {
        val supportActionBar: ActionBar? = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val supportActionBar: ActionBar? = supportActionBar
        if (supportActionBar != null) {
            onBackPressed()

            return true
        }

        return super.onSupportNavigateUp()
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog?.hide()
            mProgressDialog?.dismiss()
        }
    }

    override fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(this)
    }

    override fun onError(resId: Int) {

    }

    override fun onError(message: String?) {

    }

    override fun showMessage(message: String?) {

    }

    override fun showMessage(resId: Int) {
        
    }

    /**
     * Request permission for activity
     * @param permissions Array<String?>?
     * @param requestCode Int
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String?>?, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions!!, requestCode)
        }
    }

    /**
     * Check permission for activity
     * @param permission String?
     * @return Boolean
     */
    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String?): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission!!) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Check permission for activity
     * @param permissions Array<String?>
     * @return Boolean
     */
    fun hasPermission(permissions: Array<String?>): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission!!
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return false
            }
        }

        return true
    }
}