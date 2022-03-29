package com.gid.identifyperson.base.adapter

import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * @Author: Sothy Sin
 * @Date: 1/9/21
 */
abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(dataArray: MutableList<T>) :
    RecyclerView.Adapter<VH>() {

    private var mDataArray: MutableList<T> = dataArray


    override fun getItemCount(): Int {
        return mDataArray.size
    }

    /**
     * Add All to current list
     *
     * @param dataArray The List of object
     */
    open fun addList(dataArray: List<T>?) {
        this.mDataArray.addAll(mDataArray)
        notifyDataSetChanged()
    }

    /**
     * Adds the specified object at the end of the arrayList.
     *
     * @param object The object to add at the end of the arrayList.
     */
    open fun add(`object`: T) {
        mDataArray.add(`object`)
        notifyItemInserted(itemCount - 1)
    }

    /**
     * Remove all elements from the list.
     */
    open fun clear() {
        val size = itemCount
        mDataArray.clear()
        notifyItemRangeRemoved(0, size)
    }

    /**
     * Get item on arrayList by position
     *
     * @param position The position of item
     * @return object The object get from arrayList by position
     */
    open fun getItem(position: Int): T {
        return mDataArray[position]
    }

    /**
     *
     * @return List<T>?
     */
    open fun getAll(): List<T>? {
        return mDataArray
    }

    /**
     * Inserts the specified object at the specified index in the array.
     *
     * @param object The object to insert into the array.
     * @param index  The index at which the object must be inserted.
     */
    open fun addToIndex(`object`: T, index: Int) {
        mDataArray.add(index, `object`)
        notifyItemInserted(index)
    }

    /**
     * Returns the position of the specified item in the array.
     *
     * @param item The item(Object of arrayList) to retrieve the position of.
     * @return The position of the specified item.
     */
    open fun getPosition(item: T): Int {
        return mDataArray.indexOf(item)
    }

    /**
     * Removes the specified object from the array.
     *
     * @param object The object to remove.
     */
    open fun remove(`object`: T) {
        val position = getPosition(`object`)
        mDataArray.remove(`object`)
        notifyItemRemoved(position)
    }

    /**
     * Remove the specified position from the arrayList
     *
     * @param position The position is on arrayList
     */
    open fun remove(position: Int) {
        remove(getItem(position))
    }

    /**
     * Remove the arrayList while swipe to refresh
     */
    open fun removeAllForSwipe() {
        if (mDataArray.isNotEmpty()) {
            val size = itemCount
            mDataArray.clear()
            notifyItemRangeRemoved(0, size)
        }
    }

    /**
     * Remove all arrayList
     */
    open fun removeAll() {
        if (mDataArray.isNotEmpty()) {
            val size = itemCount
            mDataArray.clear()
            notifyItemRangeRemoved(0, size)
        }
    }

    /**
     * Sorts the content of this adapter using the specified comparator.
     *
     * @param comparator The comparator used to sort the objects contained in this adapter.
     */
    open fun sort(comparator: Comparator<in T>?) {
        Collections.sort(mDataArray, comparator)
        notifyItemRangeChanged(0, itemCount)
    }
}