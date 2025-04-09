package com.project.myrecipe.pages.generalFunctions

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

fun <T> Flow<T>.safeAsync(with:(Throwable)-> (T)) :Flow<T>{
    return this.flowOn(Dispatchers.IO).catch { emit(with(it)) }
}

fun showMessage(message:String,context: Context){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}