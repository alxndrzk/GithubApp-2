package com.example.githubapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.R
import com.example.githubapp.databinding.ActivityMainBinding
import com.example.githubapp.databinding.ActivitySplashScreenBinding
import com.example.githubapp.factory.SettingViewModelFactory
import com.example.githubapp.settings.SettingPreference
import com.example.githubapp.settings.SettingViewModel
import com.example.githubapp.settings.dataStore

class SplashScreen : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        val settingPref = SettingPreference.getInstance(application.dataStore)

        val settingsViewModel = ViewModelProvider(this, SettingViewModelFactory(settingPref)).get(
            SettingViewModel::class.java
        )

        settingsViewModel.ThemeSettingGets().observe(this){
                dackModeActive: Boolean ->
            if(dackModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
        )
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)

        viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}