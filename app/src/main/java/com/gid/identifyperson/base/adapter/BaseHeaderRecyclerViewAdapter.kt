package com.gid.identifyperson.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * @Author: Sothy Sin
 * @Date: 1/9/21
 */
abstract class BaseHeaderRecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    enum class ItemViewType(val value: Int) {
        HEADER(0), ITEM(-1), FOOTER(-2);

        companion object {
            fun fromValue(value: Int): ItemViewType {
                for (e in values()) {
                    if (e.value == value) {
                        return e
                    }
                }
                throw RuntimeException("Enum not found")
            }
        }
    }

    var mIsHasFooter = false
    var mIsHasHeader = true
    protected var mDataList: ArrayList<T> = ArrayList()

    /**
     *
     * @param dataList List<T>
     * @constructor
     */
    constructor(dataList: ArrayList<T>) {
        mDataList = dataList
    }

    /**
     *
     * @param isHasFooter Boolean
     * @param isHasHeader Boolean
     * @constructor
     */
    constructor(isHasFooter: Boolean, isHasHeader: Boolean) {
        this.mIsHasFooter = isHasFooter
        this.mIsHasHeader = isHasHeader
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (ItemViewType.fromValue(
            viewType
        )) {
            ItemViewType.ITEM -> {
                return getItemView(
                    inflater,
                    parent
                )!!
            }
            ItemViewType.HEADER -> return getHeaderView(
                inflater,
                parent
            )!!
            ItemViewType.FOOTER -> return getFooterView(
                inflater,
                parent
            )!!
        }
        throw java.lang.RuntimeException("there is no type that matches the type $viewType + make sure your using types correctly")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (ItemViewType.fromValue(
            holder.itemViewType
        )) {
            ItemViewType.ITEM -> onBindItem(
                holder,
                position)
            ItemViewType.HEADER -> onBindItemHeader(
                holder,
                position)
            ItemViewType.FOOTER -> onBindItemFooter(
                holder,
                position)
        }
    }

    override fun getItemCount(): Int {
        var itemCount = mDataList.size
        if (mIsHasHeader) {
            itemCount = mDataList.size + 1
        }
        if (mIsHasFooter) {
            itemCount++
        }

        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        if (mIsHasHeader && isPositionHeader(position)) {
            return ItemViewType.HEADER.value
        }

        if (mIsHasFooter && isPositionFooter(position)) {
            return ItemViewType.FOOTER.value
        }

        return ItemViewType.ITEM.value
    }

    /**
     *
     * @param pos Int
     * @return T
     */
    open fun getItem(pos: Int): T {
        return if (mIsHasHeader) mDataList[pos - 1] else mDataList[pos]
    }

    /**
     * Add item as object
     * @param `object` T
     */
    open fun add(`object`: T) {
        mDataList.add(`object`)
        if (mIsHasFooter && mIsHasHeader) {
            notifyItemInserted(itemCount - 2)
        } else if (mIsHasHeader && !mIsHasFooter || mIsHasFooter && !mIsHasHeader) {
            notifyItemInserted(itemCount - 1)
        } else if (!mIsHasFooter && !mIsHasHeader) {
            notifyItemInserted(itemCount)
        }
    }

    /**
     * Add item as list
     * @param list List<T>
     */
    open fun addList(list: ArrayList<T>) {
        mDataList = list
        notifyDataSetChanged()
    }

    /**
     * Add item as pagination
     * @param list List<T>
     * @param endPosition Int
     */
    open fun addListPagination(list: ArrayList<T>, endPosition: Int) {
        mDataList.addAll(list)
        notifyItemRangeInserted(endPosition, list.size)
    }

    /**
     * Add item to specific index of list
     * @param `object` T
     * @param index Int
     */
    open fun addToIndex(`object`: T, index: Int) {
        mDataList.add(index, `object`)
        notifyItemInserted(index)
    }

    /**
     * Returns the position of the specified item in the array.
     *
     * @param item The item(Object of arrayList) to retrieve the position of.
     * @return The position of the specified item.
     */
    open fun getPosition(item: T): Int {
        return mDataList.indexOf(item)
    }

    /**
     * Remove item as object
     * @param `object` T
     */
    open fun remove(`object`: T) {
        val position = getPosition(`object`)
        mDataList.remove(`object`)
        notifyItemRemoved(if (mIsHasHeader) position + 1 else position)
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
     * Remove all item while swipe to refresh
     */
    open fun removeAllForSwipe() {
        if (mDataList.isNotEmpty()) {
            itemCount
            mDataList.clear()
            /*notifyItemRangeRemoved(0, size);*/
        }
    }

    /**
     * Remove all item of list
     */
    open fun removeAll() {
        if (mDataList.isNotEmpty()) {
            val size = itemCount
            mDataList.clear()
            notifyItemRangeRemoved(0, size)
        }
    }

    /**
     * Return data as list
     * @return List<T>?
     */
    open fun getDataList(): MutableList<T> {
        return mDataList
    }

    /**
     * Sort data by comparator
     * @param comparator Comparator<in T>?
     */
    open fun sort(comparator: Comparator<in T>?) {
        Collections.sort(mDataList, comparator)
        if (mIsHasFooter && mIsHasHeader) {
            notifyItemRangeChanged(1, itemCount - 2)
        } else if (mIsHasHeader && !mIsHasFooter || mIsHasFooter && !mIsHasHeader) {
            notifyItemRangeChanged(1, itemCount - 1)
        } else if (!mIsHasFooter && !mIsHasHeader) {
            notifyItemRangeChanged(1, itemCount)
        }
    }


    /**
     *
     * @param inflater LayoutInflater?
     * @param parent ViewGroup?
     * @return RecyclerView.ViewHolder?
     */
    protected abstract fun getItemView(
        inflater: LayoutInflater?,
        parent: ViewGroup?,
    ): RecyclerView.ViewHolder?

    /**
     *
     * @param inflater LayoutInflater?
     * @param parent ViewGroup?
     * @return RecyclerView.ViewHolder?
     */
    protected open fun getHeaderView(
        inflater: LayoutInflater?,
        parent: ViewGroup?,
    ): RecyclerView.ViewHolder? {
        return null
    }

    /**
     *
     * @param inflater LayoutInflater?
     * @param parent ViewGroup?
     * @return RecyclerView.ViewHolder?
     */
    protected open fun getFooterView(
        inflater: LayoutInflater?,
        parent: ViewGroup?,
    ): RecyclerView.ViewHolder? {
        return null
    }

    /**
     * Bind item in footer
     * @param holder ViewHolder?
     * @param position Int
     */
    protected open fun onBindItemFooter(holder: RecyclerView.ViewHolder?, position: Int) {}

    /**
     * Bind item in body
     * @param holder ViewHolder?
     * @param position Int
     */
    protected abstract fun onBindItem(holder: RecyclerView.ViewHolder?, position: Int)

    /**
     * Bind item in header
     * @param holder ViewHolder?
     * @param position Int
     */
    protected open fun onBindItemHeader(holder: RecyclerView.ViewHolder?, position: Int) {}

    /**
     * Check header position
     * @param pos Int
     * @return Boolean
     */
    private fun isPositionHeader(pos: Int): Boolean {
        return pos == 0
    }

    /**
     * Check footer position
     * @param pos Int
     * @return Boolean
     */
    private fun isPositionFooter(pos: Int): Boolean {
        val lastItemIndex = itemCount - 1
        return pos == lastItemIndex
    }

}