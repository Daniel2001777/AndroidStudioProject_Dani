package com.example.projekttojas.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class SecondViewModelFactory(private val name: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SecondViewModel::class.java)){
            return SecondViewModel(name) as T
        }
        throw IllegalArgumentException("ViewModel class nem található!")
    }
}