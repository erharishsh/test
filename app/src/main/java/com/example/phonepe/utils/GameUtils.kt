package com.example.phonepe.utils

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun generateChars(name:String,size:Int): List<Char> {
    return listOf<Char>()
}

inline fun <reified T> readRawJson(context: Context, @RawRes rawResId: Int): T {
    val gson: Gson = Gson()
    context.resources.openRawResource(rawResId).bufferedReader().use {
        return gson.fromJson<T>(it, object: TypeToken<T>() {}.type)
    }
}