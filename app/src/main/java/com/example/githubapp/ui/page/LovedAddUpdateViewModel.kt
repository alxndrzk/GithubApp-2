package com.example.githubapp.ui.page

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.database.Loved
import com.example.githubapp.repository.LovedRepository

class LovedAddUpdateViewModel(application: Application): ViewModel() {

    private val userLovedRepository: LovedRepository = LovedRepository(application)

    fun getAllLovedByName(username: String) : LiveData<Loved> = userLovedRepository.getAllLovedByName(username)

    fun insert(loved: Loved) {
        userLovedRepository.insert(loved)
    }
    fun update(loved: Loved) {
        userLovedRepository.update(loved)
    }
    fun delete(loved: Loved) {
        userLovedRepository.delete(loved)
    }


}