package com.example.githubapp.ui.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.databinding.ActivityLovedListBinding
import com.example.githubapp.factory.LovedViewModelFactory

class LovedListActivity : AppCompatActivity() {


    private var _lovedActivityBinding : ActivityLovedListBinding? = null
    private val binding get() = _lovedActivityBinding
    private lateinit var adapter: LovedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _lovedActivityBinding = ActivityLovedListBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val lovedViewModel = obtainViewModel(this@LovedListActivity)
        lovedViewModel.getAllLoved().observe(this) { lovedList ->

                adapter.setListLoved(lovedList)

        }

        adapter = LovedAdapter()
        binding?.rvLoved?.layoutManager = LinearLayoutManager(this)
        binding?.rvLoved?.setHasFixedSize(true)
        binding?.rvLoved?.adapter = adapter

        binding?.btnLovedBack?.setOnClickListener {
            onBackPressed()
        }

    }


    private fun obtainViewModel(activity: AppCompatActivity): LovedViewModel {
        val factory = LovedViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(LovedViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _lovedActivityBinding= null
    }
}