package com.jmperezra.bluey.feature.characters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jmperezra.bluey.R
import com.jmperezra.bluey.feature.characters.domain.GetCharactersUseCase

class CharactersAdapter :
    ListAdapter<GetCharactersUseCase.Output, CharacterViewHolder>(CharacterDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}


class CharacterDiffUtil : DiffUtil.ItemCallback<GetCharactersUseCase.Output>() {

    override fun areItemsTheSame(
        oldItem: GetCharactersUseCase.Output,
        newItem: GetCharactersUseCase.Output
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GetCharactersUseCase.Output,
        newItem: GetCharactersUseCase.Output
    ): Boolean {
        return oldItem == newItem
    }
}