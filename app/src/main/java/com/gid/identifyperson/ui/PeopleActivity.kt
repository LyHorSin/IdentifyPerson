package com.gid.identifyperson.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gid.identifyperson.R
import com.gid.identifyperson.adapters.PeopleAdapter
import com.gid.identifyperson.base.BaseActivity
import com.gid.identifyperson.databinding.ActivityPeopleBinding

class PeopleActivity : BaseActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, PeopleActivity::class.java)
            context.startActivity(starter)
        }
    }

    private lateinit var mBinding: ActivityPeopleBinding
    private lateinit var mPeopleAdapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initPeopleRecycler()
    }

    override fun getLayout(): View {
        mBinding = ActivityPeopleBinding.inflate(layoutInflater)
        return mBinding.root
    }

    private fun initToolbar() {
        mBinding.inclToolbar.toolbar.title = getString(R.string.people_information)
        setSupportActionBar(mBinding.inclToolbar.toolbar)
        showBackArrow()
    }

    private fun initPeopleRecycler() {
        mPeopleAdapter = PeopleAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.recyclerPeople.layoutManager = layoutManager
        mBinding.recyclerPeople.adapter = mPeopleAdapter
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        mBinding.recyclerPeople.addItemDecoration(itemDecorator)
    }
}