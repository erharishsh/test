package com.example.phonepe.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.phonepe.data.GameRepo
import com.example.phonepe.ui.main.GameViewModel


class GameViewModelFactory(
    private val gameRepo: GameRepo,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(gameRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}
