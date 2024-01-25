package com.example.imagesearch.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imagesearch.R
import com.example.imagesearch.data.Document
import com.example.imagesearch.databinding.ActivityMainBinding
import com.example.imagesearch.fragment.MyLockerFragment
import com.example.imagesearch.fragment.SearchImageFragment
import com.example.imagesearch.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var searchItem = listOf<Document>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnSearch.setOnClickListener {
                val query = binding.etSearch.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val responseData = RetrofitInstance.imageSearchApi.getImage(query = query)
                    searchItem = responseData.documents
                }

            }
            btnSearchImagePage.setOnClickListener {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.container,SearchImageFragment())
                        commit()
                    }
            }
            btnMyLockerPage.setOnClickListener {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.container,MyLockerFragment())
                    commit()
                }
            }


        }
    }
}