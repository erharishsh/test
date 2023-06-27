package com.example.phonepe.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonepe.data.GameRepo
import com.example.phonepe.domain.Game
import com.example.phonepe.utils.generateChars
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val INPUT_CHAR_LIST_SIZE: Int = 10

class GameViewModel(private val gameRepo: GameRepo) : ViewModel() {

    private val _gameList: MutableList<Game> = mutableListOf()


    private val _gameLiveData: MutableLiveData<Game> = MutableLiveData()
    val gameLiveData: LiveData<Game> = _gameLiveData

    private var _currentIndex:Int = -1


    init {
        getGameData()
    }

    private fun getGameData() {
        //error handling needs to be done
        viewModelScope.launch(Dispatchers.IO) {
            val result = gameRepo.getGameData()
            _gameList.clear()
            _gameList.addAll(result)
            _currentIndex = -1
            switchToNextGamePage()
        }

    }

    fun getListOfInputCharsForCurrentPage(): List<Char> {
        _gameLiveData.value?.name?.let {
           return generateChars(it, INPUT_CHAR_LIST_SIZE)
        }
        return listOf()
    }

    fun checkName(input: String): Boolean {
        _gameLiveData.value?.name.let {
            return it.equals(input)
        }
    }

    fun switchToNextGamePage() {
        if (_currentIndex < (_gameList.size) - 1) {
            _gameLiveData.postValue(_gameList[++_currentIndex])
        }
    }


}