package com.gid.identifyperson.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gid.identifyperson.R
import com.gid.identifyperson.ui.fragment.FamilyInformationFragment
import com.gid.identifyperson.ui.fragment.PersonalInformationFragment

class PeopleFormPagerAdapter constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val mListTab = listOf(
        Pair(PersonalInformationFragment(), R.string.personal_information),
        Pair(FamilyInformationFragment(), R.string.family_information),
    )

    override fun createFragment(position: Int): Fragment {
        return mListTab[position].first
    }

    override fun getItemCount(): Int {
        return mListTab.count()
    }
}