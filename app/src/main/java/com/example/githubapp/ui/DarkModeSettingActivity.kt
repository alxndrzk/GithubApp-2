package com.example.githubapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.R
import com.example.githubapp.factory.SettingViewModelFactory
import com.example.githubapp.settings.SettingPreference
import com.example.githubapp.settings.SettingViewModel
import com.example.githubapp.settings.dataStore

class DarkModeSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dark_mode_setting)

        val userThemeSwitcher = findViewById<Switch>(R.id.dark_or_light)

        val settingPref = SettingPreference.getInstance(application.dataStore)

        val settingsViewModel = ViewModelProvider(this,SettingViewModelFactory(settingPref)).get(
            SettingViewModel::class.java
        )



        settingsViewModel.ThemeSettingGets().observe(this){
            dackModeActive: Boolean ->
            if(dackModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                userThemeSwitcher.text = "Dark mode"

                userThemeSwitcher.isChecked = true
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                userThemeSwitcher.text = "Ligth Mode"
                userThemeSwitcher.isChecked = false
            }

        }

        userThemeSwitcher.setOnCheckedChangeListener{ _: CompoundButton?, isSwitch: Boolean ->

           settingsViewModel.ThemeSettingsSave(isSwitch)

        }


        val btnBackSetting = findViewById<ImageView>(R.id.btn_settings_back)

        btnBackSetting.setOnClickListener {
            onBackPressed()
        }

    }
}