package com.example.imagesearch.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearch.adapter.ImageAdapter
import com.example.imagesearch.data.Document
import com.example.imagesearch.databinding.FragmentSearchImageBinding
import com.example.imagesearch.listener.ImageClickListener
import com.example.imagesearch.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchImageFragment : Fragment(), ImageClickListener {
    private var _binding: FragmentSearchImageBinding? = null
    private val binding get() = _binding!!
    private var items = mutableListOf<Document>()
    private lateinit var imageAdapter: ImageAdapter
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
                if (query.isEmpty()) return@setOnClickListener
                //Api 연결 시 IO 으로 연결 하고, Ui갱신은 withContext로 Main에서 처리
                CoroutineScope(Dispatchers.IO).launch {
                    val responseData = RetrofitInstance.imageSearchApi.getImage(query = query)
                    items.addAll(responseData.documents)
                    withContext(Dispatchers.Main) {
                        initRecyclerView()
                    }
                }
                downKeyBoard(requireContext(), etSearch)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            imageAdapter = ImageAdapter(context, this@SearchImageFragment)
            adapter = imageAdapter
            layoutManager = GridLayoutManager(context, 2)
            imageAdapter.submitList(items)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClickImage(document: Document) {
        document.isLike = !document.isLike
        //imageAdapter.submitList(items)
        imageAdapter.notifyDataSetChanged()
    }
    //키보드 내리기
    private fun downKeyBoard(context: Context, editText: EditText) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        editText.clearFocus()
        inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}