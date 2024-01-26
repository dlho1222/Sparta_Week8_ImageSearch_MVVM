package com.example.imagesearch.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.data.Document
import com.example.imagesearch.databinding.ItemImageBinding
import com.example.imagesearch.listener.ImageClickListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ImageAdapter(
    private val context: Context,
    private val listener: ImageClickListener? = null
) :
    ListAdapter<Document, ImageAdapter.ImageViewHolder>(object : DiffUtil.ItemCallback<Document>() {
        override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
            Log.i("TAG", "areItemsTheSame: ${oldItem.isLike} == ${newItem.isLike}")
            return oldItem.isLike == newItem.isLike
        }

        override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem == newItem
        }
    }) {
    inner class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(document: Document) {
            val dateTime = document.datetime
            val inputDateTime =
                LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val outputDateString = inputDateTime.format(outputFormatter)
            with(binding) {
                tvSource.text = document.displaySiteName
                tvDate.text = outputDateString.toString()

                Glide.with(context)
                    .load(document.thumbnailUrl)
                    .into(ivThumb)
                ivLike.isVisible = document.isLike

                ivThumb.setOnClickListener {
                    listener?.onClickImage(document)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemImageBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val documents = currentList[position]
        holder.bind(documents)
    }
}