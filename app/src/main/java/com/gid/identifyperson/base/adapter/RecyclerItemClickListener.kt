package com.gid.identifyperson.base.adapter

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

/**
 * @Author: Sothy Sin
 * @Date: 11/10/21
 */
class RecyclerItemClickListener constructor(context: Context, private val recyclerView: RecyclerView, listener: OnItemClickListener) : RecyclerView.OnItemTouchListener {

    private var mListener: OnItemClickListener? = null
    private var mGestureDetector: GestureDetector? = null

    init {
        mListener = listener
        mGestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }

            override fun onLongPress(e: MotionEvent) {
                val childView = recyclerView.findChildViewUnder(e.x, e.y)
                if (!(childView == null || mListener == null)) {
                    mListener?.onItemLongClick(
                        childView,
                        recyclerView.getChildAdapterPosition(childView)
                    )
                }
            }
        })
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView: View? = view.findChildViewUnder(e.x, e.y)
        try {
            if (mListener != null && mGestureDetector!!.onTouchEvent(e)) {
                if (childView != null) {
                    mListener!!.onItemClick(childView, view.getChildAdapterPosition(childView))
                }
            }
        } catch (e1: Exception) {
            e1.printStackTrace()
        }

        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        TODO("Not yet implemented")
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        TODO("Not yet implemented")
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }
}