package com.example.phonepe.data

import com.example.phonepe.datasource.GameDataSource
import com.example.phonepe.domain.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameRepo(private val gameDataSource: GameDataSource) {


    suspend fun getGameData(): List<Game> {
        return withContext(Dispatchers.IO) {
            //error handling needs to be done
            val gameResponse: List<GameResponse> = gameDataSource.getGameData();
            return@withContext gameResponse.toGame()
        }
    }


}
