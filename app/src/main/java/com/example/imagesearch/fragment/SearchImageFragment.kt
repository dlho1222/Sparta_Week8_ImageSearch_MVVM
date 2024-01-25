package com.example.imagesearch.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearch.adapter.ImageAdapter
import com.example.imagesearch.data.Document
import com.example.imagesearch.databinding.FragmentSearchImageBinding
import com.example.imagesearch.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchImageFragment : Fragment() {
    private var _binding: FragmentSearchImageBinding? = null
    private val binding get() = _binding!!
    private var items = mutableListOf<Document>()
    private val imageAdapter by lazy { context?.let { ImageAdapter(items, it) } }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btnSearch.setOnClickListener {
                val query = etSearch.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val responseData = RetrofitInstance.imageSearchApi.getImage(query = query)
                    items = responseData.documents
                    withContext(Dispatchers.Main){
                        recyclerView.apply {
                            adapter = imageAdapter
                            layoutManager = GridLayoutManager(context,2)
                        }
                    }

                    Log.i("TAG", "$responseData")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}