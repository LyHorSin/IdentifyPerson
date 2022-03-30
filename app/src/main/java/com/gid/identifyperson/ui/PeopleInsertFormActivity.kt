package com.gid.identifyperson.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.gid.identifyperson.R
import com.gid.identifyperson.adapters.PeopleFormPagerAdapter
import com.gid.identifyperson.base.BaseActivity
import com.gid.identifyperson.databinding.ActivityPeopleInsertFormBinding

class PeopleInsertFormActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, PeopleInsertFormActivity::class.java)
            context.startActivity(starter)
        }
    }

    private lateinit var mBinding: ActivityPeopleInsertFormBinding
    private lateinit var mPeopleFormPagerAdapter: PeopleFormPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initPeoplePager()
    }

    override fun getLayout(): View {
        mBinding = ActivityPeopleInsertFormBinding.inflate(layoutInflater)
        return mBinding.root
    }

    private fun initToolbar() {
        mBinding.inclToolbar.toolbar.title = getString(R.string.fill_your_information)
        setSupportActionBar(mBinding.inclToolbar.toolbar)
        showBackArrow()
    }

    private fun initPeoplePager() {
        val viewPager = mBinding.pagerPeople
        mPeopleFormPagerAdapter = PeopleFormPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.isUserInputEnabled = false
        viewPager.adapter = mPeopleFormPagerAdapter
    }
}