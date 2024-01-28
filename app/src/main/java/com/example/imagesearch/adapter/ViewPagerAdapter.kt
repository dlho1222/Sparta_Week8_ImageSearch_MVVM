package com.example.imagesearch.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.imagesearch.activity.MainActivity
import com.example.imagesearch.fragment.MyLockerFragment
import com.example.imagesearch.fragment.SearchImageFragment

class ViewPagerAdapter(private val mainActivity: MainActivity) :
    FragmentStateAdapter(mainActivity) {
    private val searchImageFragment by lazy { SearchImageFragment() }
    private val myLockerFragment by lazy { MyLockerFragment() }
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> searchImageFragment
            else -> myLockerFragment
        }
    }
}