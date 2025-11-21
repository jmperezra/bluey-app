package com.jmperezra.bluey.feature.characters.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.jmperezra.bluey.R
import com.jmperezra.bluey.core.domain.ErrorApp
import com.jmperezra.bluey.core.presentation.errors.ErrorAppFactory
import com.jmperezra.bluey.core.presentation.ext.fold
import com.jmperezra.bluey.core.presentation.ext.hide
import com.jmperezra.bluey.databinding.FragmentCharactersBinding
import com.jmperezra.bluey.feature.characters.domain.GetCharactersUseCase
import com.jmperezra.bluey.feature.characters.presentation.adapter.CharactersAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null;
    private val binding get() = _binding!!

    val viewModel: CharactersViewModel by viewModel()

    private val characterAdapter: CharactersAdapter by inject()
    private val errorFactory: ErrorAppFactory by inject()
    private lateinit var skeleton: Skeleton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        val view = binding.root
        setupView()
        return view
    }

    private fun setupView() {
        binding.apply {
            listCharacters.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = characterAdapter
                skeleton = applySkeleton(R.layout.view_item_character, 15)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.loadCharacters()
    }

    private fun setupObserver() {
        val observer = Observer<CharactersViewModel.UiState> { uiState ->
            bindLoading(uiState.isLoading)
            bindError(uiState.errorApp)
            bindData(uiState.characters)
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindLoading(isLoading: Boolean) {
        isLoading.fold({ skeleton.showSkeleton() }, { skeleton.showOriginal() })
    }

    private fun bindError(errorApp: ErrorApp?) {
        errorApp?.let { errorApp ->
            val errorAppUi = errorFactory.build(errorApp) {
                viewModel.loadCharacters()
            }
            binding.errorView.render(errorAppUi)
        } ?: run {
            binding.errorView.hide()
        }
    }

    private fun bindData(characters: List<GetCharactersUseCase.Output>?) {
        characterAdapter.submitList(characters)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}