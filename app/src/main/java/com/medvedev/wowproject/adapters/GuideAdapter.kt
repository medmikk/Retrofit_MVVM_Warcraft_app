package com.medvedev.wowproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.medvedev.wowproject.R
import com.medvedev.wowproject.model.GuideDTO
import com.medvedev.wowproject.model.SpecDTO
import kotlinx.android.synthetic.main.item_guide.view.*
import kotlinx.android.synthetic.main.item_ranking.view.*

class GuideAdapter : RecyclerView.Adapter<GuideAdapter.GuideViewHolder>(){
    inner class GuideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<GuideDTO>(){
        override fun areItemsTheSame(oldItem: GuideDTO, newItem: GuideDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GuideDTO, newItem: GuideDTO): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        return GuideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_guide,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GuideAdapter.GuideViewHolder, position: Int) {
        val guide = differ.currentList[position]
        holder.itemView.apply{
            Glide.with(this).load(guide.img_link).into(guideHomeIV)
            guideNameTV.text = guide.spec_name

            setOnClickListener{
                onItemClickListener?.let{ it(guide) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((GuideDTO) -> Unit)? = null

    fun setOnItemClickListener(listener: (GuideDTO) -> Unit) {
        onItemClickListener = listener
    }
}