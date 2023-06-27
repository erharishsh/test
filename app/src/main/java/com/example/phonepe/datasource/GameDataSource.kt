package com.example.phonepe.datasource

import com.example.phonepe.data.GameResponse

interface GameDataSource {

    suspend fun getGameData():List<GameResponse>

}