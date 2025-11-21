package com.jmperezra.bluey.feature.characters.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jmperezra.bluey.core.presentation.ext.fromUrl
import com.jmperezra.bluey.databinding.ViewItemCharacterBinding
import com.jmperezra.bluey.feature.characters.domain.GetCharactersUseCase

class CharacterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewItemCharacterBinding

    fun bind(character: GetCharactersUseCase.Output) {
        binding = ViewItemCharacterBinding.bind(view)
        binding.apply {
            nameCharacter.text = character.name
            shortDescriptionCharacter.text = character.shortDescription
            imageCharacter.fromUrl(character.urlPhoto)
        }
    }
}
