package com.example.phonepe.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.phonepe.data.GameRepo
import com.example.phonepe.databinding.GameFragmentBinding
import com.example.phonepe.datasource.LocalGameDataSource
import com.example.phonepe.domain.Game
import com.example.phonepe.ui.viewModelFactory.GameViewModelFactory

class GameFragment : Fragment() {

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel
    private var _binding: GameFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory =
            GameViewModelFactory(GameRepo(LocalGameDataSource(requireContext()))) // need to use DI here
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[GameViewModel::class.java]
    }


    fun addNameChangeListener() {
        binding.input.doOnTextChanged { text, start, before, count ->
            if (count == viewModel.gameLiveData.value?.name?.length) {
                onInputComplete()
            }
        }
    }

    private fun addObservers() {

        viewModel.gameLiveData.observe(viewLifecycleOwner) {
            // update the UI with new Game
            updateUI(it)
        }

    }


    private fun updateUI(game: Game) {
        Glide.with(requireContext()).load(game.imageUrl).into(binding.logo);
        addButtonsToInputLayout(viewModel.getListOfInputCharsForCurrentPage())

    }

    private fun addButtonsToInputLayout(chars: List<Char>) {
        //add button to input and click listeners to them
    }


    private fun onInputComplete() {
        if (viewModel.checkName("")) {
            viewModel.switchToNextGamePage()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        addNameChangeListener()
    }


}