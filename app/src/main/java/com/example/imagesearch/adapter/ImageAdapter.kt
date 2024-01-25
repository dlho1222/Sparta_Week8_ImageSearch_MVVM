package com.example.imagesearch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearch.data.Document
import com.example.imagesearch.databinding.ItemImageBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ImageAdapter(private val items: MutableList<Document>, private val context: Context) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(document: Document) {
            val dateTime = document.datetime
            val inputDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val outputDateString = inputDateTime.format(outputFormatter)
            with(binding) {
                tvSource.text = document.display_sitename
                tvDate.text = outputDateString.toString()
                Glide.with(context)
                    .load(document.thumbnail_url)
                    .into(ivThumb)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemImageBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position])
    }
}