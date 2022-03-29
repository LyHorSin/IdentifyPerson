package com.gid.identifyperson.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gid.identifyperson.base.adapter.BaseHeaderRecyclerViewAdapter
import com.gid.identifyperson.databinding.ItemListPeopleBinding

class PeopleAdapter : BaseHeaderRecyclerViewAdapter<String>(false, false) {

    override fun getItemView(
        inflater: LayoutInflater?,
        parent: ViewGroup?
    ): RecyclerView.ViewHolder? {
        val binding =
            ItemListPeopleBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return PeopleHolder(binding)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindItem(holder: RecyclerView.ViewHolder?, position: Int) {

    }

    internal class PeopleHolder(viewBinding: ItemListPeopleBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        val mBinding: ItemListPeopleBinding = viewBinding
    }
}