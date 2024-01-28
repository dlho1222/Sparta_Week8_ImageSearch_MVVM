package com.example.imagesearch.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.imagesearch.R
import com.example.imagesearch.databinding.ActivityMainBinding
import com.example.imagesearch.fragment.MyLockerFragment
import com.example.imagesearch.fragment.SearchImageFragment

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val searchImageFragment by lazy{SearchImageFragment()}
    private val myLockerFragment by lazy { MyLockerFragment() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(searchImageFragment)

        with(binding) {
            btnSearchImagePage.setOnClickListener {
                setFragment(searchImageFragment)
            }
            btnMyLockerPage.setOnClickListener {
                setFragment(myLockerFragment)
            }
        }
    }
    private fun setFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, frag)
            commit()
        }
    }
}