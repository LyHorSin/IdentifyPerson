package com.gid.identifyperson.ui.fragment

import android.os.Bundle
import android.view.View
import com.gid.identifyperson.base.BaseFragment
import com.gid.identifyperson.databinding.FragmentPersonalInformationBinding

class PersonalInformationFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            PersonalInformationFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private lateinit var mBinding: FragmentPersonalInformationBinding

    override fun getLayout(): View {
        mBinding = FragmentPersonalInformationBinding.inflate(layoutInflater)
        return  mBinding.root
    }
}