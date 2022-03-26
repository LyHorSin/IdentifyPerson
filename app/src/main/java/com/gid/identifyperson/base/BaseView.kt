package com.gid.identifyperson.base

import androidx.annotation.StringRes

/**
 * @Author: Sothy Sin
 * @Date: 1/9/21
 */
interface BaseView {

    /**
     * Show loading when view action
     */
    fun showLoading()

    /**
     * Hide loading when view action
     */
    fun hideLoading()

    /**
     * Show error when view action
     * @param resId Int
     */
    fun onError(@StringRes resId: Int)

    /**
     * Show error when view action
     * @param message String?
     */
    fun onError(message: String?)

    /**
     * Show message when view action
     * @param message String?
     */
    fun showMessage(message: String?)

    /**
     * Show message when view action
     * @param resId Int
     */
    fun showMessage(@StringRes resId: Int)

    /**
     * Check network connection
     * @return Boolean
     */
    fun isNetworkConnected(): Boolean

    /**
     * Handle keyboard invisible
     */
    fun hideKeyboard()

    /**
     * Handle keyboard visible
     */
    fun showBackArrow()
}