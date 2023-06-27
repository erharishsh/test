package com.example.phonepe.datasource

import android.content.Context
import com.example.phonepe.R
import com.example.phonepe.data.GameResponse
import com.example.phonepe.utils.readRawJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalGameDataSource(private val context: Context) : GameDataSource {

    override suspend fun getGameData(): List<GameResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext readRawJson(context, R.raw.test)
        }
    }
}