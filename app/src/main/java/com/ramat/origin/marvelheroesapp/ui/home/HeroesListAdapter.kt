package com.ramat.origin.marvelheroesapp.ui.home

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ramat.origin.marvelheroesapp.R
import com.ramat.original.moviescatalog.model.Result
import kotlinx.android.synthetic.main.item_hero_list.view.*

class MarvelCharacterAdapter(private val character: MutableList<Result>):
        RecyclerView.Adapter<MarvelCharacterAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_hero_list))
    }

    override fun onBindViewHolder(holder: MarvelCharacterAdapter.ViewHolder, position: Int) {
        holder.bind(character[position])
    }

    override fun getItemCount() = character.size

    fun updateList(character: MutableList<Result>){
        this.character.clear()
        this.character.addAll(character)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var char: Result

        fun bind(result: Result){
            this.char = result
            //itemView.image_thumbnail.setImageResource(result.thumbnail.path)
            itemView.text_id.text = result.id.toString()
            itemView.text_name.text = result.name
        }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}