package com.jmperezra.bluey.feature.characters.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jmperezra.bluey.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment : Fragment() {

    val args: CharacterDetailFragmentArgs by navArgs()

    private var _binding: FragmentCharacterDetailBinding? = null;
    private val binding get() = _binding!!

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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}