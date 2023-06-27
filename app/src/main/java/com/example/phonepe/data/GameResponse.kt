package com.example.phonepe.data

import com.example.phonepe.domain.Game


data class GameResponse(val imageUrl:String,val name:String)


fun List<GameResponse>.toGame(): List<Game> {
    return map { Game(it.imageUrl, it.name) }
}