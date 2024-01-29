package com.example.imagesearch.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imagesearch.adapter.ImageAdapter
import com.example.imagesearch.data.Document
import com.example.imagesearch.databinding.FragmentMyLockerBinding
import com.example.imagesearch.listener.ImageClickListener
import com.example.imagesearch.manager.DocumentsManager

class MyLockerFragment : Fragment(), ImageClickListener {
    private var _binding: FragmentMyLockerBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyLockerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val likeContents = DocumentsManager.getLikeContents()
        imageAdapter =
            ImageAdapter(requireContext(), this@MyLockerFragment, likeContents = likeContents)
        binding.recyclerView.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
        imageAdapter.submitList(likeContents) //좋아요 버튼 눌린 이미지만 표시
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //이미지 클릭시 토글
    override fun onClickImage(document: Document, position: Int) {
        DocumentsManager.toggleLike(document)
        val likeContents = DocumentsManager.getLikeContents()
        imageAdapter.submitList(likeContents)
    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
    }
}