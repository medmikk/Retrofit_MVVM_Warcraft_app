package com.medvedev.wowproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.medvedev.wowproject.R
import com.medvedev.wowproject.model.SpecDTO
import kotlinx.android.synthetic.main.item_ranking.view.*

class RankingAdapter : RecyclerView.Adapter<RankingAdapter.RankingViewHolder>(){

    inner class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<SpecDTO>(){
        override fun areItemsTheSame(oldItem: SpecDTO, newItem: SpecDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SpecDTO, newItem: SpecDTO): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        return RankingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_ranking,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val spec = differ.currentList[position]
        holder.itemView.apply{
            specNameTV.text = spec.name
            specDpsTV.text = spec.dps
            specPercentTV.text = spec.proc
            setOnClickListener{
                onItemClickListener?.let{ it(spec) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((SpecDTO) -> Unit)? = null

    fun setOnItemClickListener(listener: (SpecDTO) -> Unit) {
        onItemClickListener = listener
    }
}