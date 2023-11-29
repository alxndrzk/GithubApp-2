package com.example.githubapp.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.ui.page.LovedAddUpdateViewModel
import com.example.githubapp.ui.page.LovedViewModel

class LovedViewModelFactory private constructor(private val application : Application):ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: LovedViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): LovedViewModelFactory {
            if (INSTANCE == null) {
                synchronized(LovedViewModelFactory::class.java) {
                    INSTANCE = LovedViewModelFactory(application)
                }
            }
            return INSTANCE as LovedViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LovedViewModel::class.java)) {
            return LovedViewModel(application) as T
        } else if (modelClass.isAssignableFrom(LovedAddUpdateViewModel::class.java)) {
            return LovedAddUpdateViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}