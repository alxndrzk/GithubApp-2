package com.example.githubapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(Activity: AppCompatActivity,data:Bundle):FragmentStateAdapter(Activity) {

    private var fragment: Bundle

    init {
        fragment = data
    }


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FollowersListFragment()
            }
            1 -> fragment = FollowingListFragment()
        }
        fragment?.arguments = this.fragment
        return fragment as Fragment
    }
}