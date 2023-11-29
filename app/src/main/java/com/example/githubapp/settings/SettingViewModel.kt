package com.example.githubapp.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SettingViewModel(private val themeSettingPref: SettingPreference): ViewModel() {
    fun ThemeSettingGets(): LiveData<Boolean>{
        return  themeSettingPref.getThemeSetting().asLiveData()
    }

    fun ThemeSettingsSave(activeDarkMode : Boolean){
        viewModelScope.launch {
            themeSettingPref.saveThemeSetting(activeDarkMode)
        }
    }
}