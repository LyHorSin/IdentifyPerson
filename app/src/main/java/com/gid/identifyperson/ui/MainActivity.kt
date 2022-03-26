package com.gid.identifyperson.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.gid.identifyperson.R
import com.gid.identifyperson.base.BaseActivity
import com.gid.identifyperson.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initView()
    }

    override fun getLayout(): View {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        return mBinding.root
    }

    private fun initToolbar() {
        mBinding.inclToolbar.toolbar.title = getString(R.string.welcome)
        setSupportActionBar(mBinding.inclToolbar.toolbar)
    }

    private fun initView() {
        val items = listOf("Phnom Penh", "Kompong Cham", "Kompong Thom", "Siem Reap")
        val adapter = ArrayAdapter(this, R.layout.item_list_address, items)
        (mBinding.inputProvince as? AutoCompleteTextView)?.setAdapter(adapter)
        mBinding.inputProvince.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Selected Item: " + adapter.getItem(position), Toast.LENGTH_SHORT).show()
        }
    }
}