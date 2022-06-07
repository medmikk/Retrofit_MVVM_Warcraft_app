package com.medvedev.wowproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.medvedev.wowproject.R
import com.medvedev.wowproject.model.SpecDTO
import com.medvedev.wowproject.model.SpellDTO
import com.medvedev.wowproject.util.GlideLoader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_spell.view.*

class SpecAdapter : RecyclerView.Adapter<SpecAdapter.SpecViewHolder>() {

    inner class SpecViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<SpellDTO>(){
        override fun areItemsTheSame(oldItem: SpellDTO, newItem: SpellDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SpellDTO, newItem: SpellDTO): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecViewHolder {
        return SpecViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_spell,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SpecViewHolder, position: Int) {
        val spell = differ.currentList[position]
        holder.itemView.apply{
            //Тут ничего не грузится, хз почему TODO ДОДЕЛАТЬ ЗАГРУЗКУ
//            if(spell.img_link != null) {
//                Picasso.get().load("https://media.giphy.com/media/SKGo6OYe24EBG/giphy.gif").into(spellIV)
//                Glide.with(this).load(spell.img_link).into(spellIV)
//            }
            Glide.with(this).load("https://media.giphy.com/media/SKGo6OYe24EBG/giphy.gif").into(spellIV)
            spellNameTV.text = spell.name
            spellDpsTV.text = spell.damage
            setOnClickListener{
                onItemClickListener?.let{ it(spell) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((SpellDTO) -> Unit)? = null

    fun setOnItemClickListener(listener: (SpellDTO) -> Unit) {
        onItemClickListener = listener
    }
}