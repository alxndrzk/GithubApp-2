package com.example.githubapp.ui

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.R
import com.example.githubapp.data.response.ItemsItem
import com.example.githubapp.databinding.ActivityMainBinding
import com.example.githubapp.factory.LovedViewModelFactory
import com.example.githubapp.settings.SettingViewModel
import com.example.githubapp.ui.page.LovedListActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var usersAdapter: UsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        val itemDecorator = DividerItemDecoration(this,layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecorator)
        usersAdapter = UsersAdapter()
        binding.rvUser.adapter = usersAdapter

        val fromMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val mainViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java)



        with(binding){
            searchBar.inflateMenu(R.menu.love_setting)
            val srcMenu = searchBar.menu
            val lovedMenuItem = srcMenu.findItem(R.id.itemlove)
            val settingMenuItem = srcMenu.findItem(R.id.itemsettings)
            val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK


            if(currentNightMode == Configuration.UI_MODE_NIGHT_YES ){
                lovedMenuItem.setIcon(R.drawable.ic_favorite_fill_white)
                settingMenuItem.setIcon(R.drawable.baseline_settings_24)
            }else{
                lovedMenuItem.setIcon(R.drawable.ic_favorite_fill)
                settingMenuItem.setIcon(R.drawable.ic_settings)
            }



            lovedMenuItem.setOnMenuItemClickListener {
                val itn = Intent(this@MainActivity,LovedListActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(itn)
                true
            }
            settingMenuItem.setOnMenuItemClickListener {
                val intent = Intent(this@MainActivity, DarkModeSettingActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
                true
            }
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnClickListener{
                showLoading(true)
                searchBar.text = searchView.text

                searchView.hide()

                mainViewModel.findUsernameAccount(searchView.text.toString())
            }

        }


        mainViewModel.findUsernameAccount("a")

        mainViewModel.listReview.observe(this){
            username -> setReveiewNameData(username)
        }

        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }
        
    }

    private fun obtainViewModel(activity: AppCompatActivity): SettingViewModel {
        val factory = LovedViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(SettingViewModel::class.java)
    }


    private fun setReveiewNameData(usernameData:List<ItemsItem>){
        val adapter =UsersAdapter()
        adapter.submitList(usernameData)
        binding.rvUser.adapter = adapter
//
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}