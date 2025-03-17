package com.example.rilexmovie.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rilexmovie.Domain.FilmItemModel
import com.example.rilexmovie.Repository.MainRepository

class MainViewModel:ViewModel() {

    private val repository=MainRepository()

    fun loadUpcoming():LiveData<MutableList<FilmItemModel>> {
        return repository.loadUpcoming()
    }
    fun loadItems():LiveData<MutableList<FilmItemModel>>{
        return repository.loadItems()
    }
}