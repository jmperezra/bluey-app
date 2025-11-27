package com.jmperezra.bluey.feature.characters.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.createSkeleton
import com.jmperezra.bluey.core.domain.ErrorApp
import com.jmperezra.bluey.core.presentation.errors.ErrorAppFactory
import com.jmperezra.bluey.core.presentation.ext.fold
import com.jmperezra.bluey.core.presentation.ext.fromUrl
import com.jmperezra.bluey.core.presentation.ext.hide
import com.jmperezra.bluey.databinding.FragmentCharacterDetailBinding
import com.jmperezra.bluey.feature.characters.domain.GetCharacterDetailUseCase
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment : Fragment() {

    val args: CharacterDetailFragmentArgs by navArgs()
    val characterId: String by lazy { args.characterId }

    private var _binding: FragmentCharacterDetailBinding? = null;
    private val binding get() = _binding!!

    private val viewModel: CharacterDetailViewModel by viewModel()
    private val errorFactory: ErrorAppFactory by inject()
    private lateinit var skeleton: Skeleton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        setupView()
        return view
    }

    private fun setupView() {
        skeleton = binding.contentLayout.createSkeleton()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.loadCharacter(characterId)
    }

    private fun setupObserver() {
        val observer = Observer<CharacterDetailViewModel.UiState> { uiState ->
            bindLoading(uiState.isLoading)
            bindError(uiState.errorApp)
            bindData(uiState.character)
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindLoading(isLoading: Boolean) {
        isLoading.fold({ skeleton.showSkeleton() }, { skeleton.showOriginal() })
    }

    private fun bindError(errorApp: ErrorApp?) {
        errorApp?.let { errorApp ->
            val errorAppUi = errorFactory.build(errorApp) {
                viewModel.loadCharacter(characterId)
            }
            binding.errorView.render(errorAppUi)
        } ?: run {
            binding.errorView.hide()
        }
    }

    private fun bindData(character: GetCharacterDetailUseCase.Output?) {
        character?.let {
            binding.apply {
                characterImage.fromUrl(it.urlPhoto)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}