package com.example.imagesearch.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imagesearch.R
import com.example.imagesearch.adapter.ViewPagerAdapter
import com.example.imagesearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            viewPager.adapter = ViewPagerAdapter(this@MainActivity)
            BottomNavigationBar.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.action_ImageSearchPage -> viewPager.currentItem = 0
                    R.id.action_MyLockerPage -> viewPager.currentItem = 1
                }
                true
            }
            BottomNavigationBar.itemIconTintList = null
        }
    }
}