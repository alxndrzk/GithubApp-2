package com.example.githubapp.ui

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.githubapp.data.response.UserFollowersResponseItem
import com.example.githubapp.data.retrofit.ApiConfig
import retrofit2.Call

import retrofit2.Response

class FollowersListViewModel: ViewModel() {

    private val _followersList = MutableLiveData<List<UserFollowersResponseItem>>()
    val followersList: LiveData<List<UserFollowersResponseItem>> = _followersList

    private val _isloading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isloading


    fun findUsernameFollowAccount(data: String){
        _isloading.value = true
        val client = ApiConfig.getApiService().getUserFollowers(data)
        client.enqueue(object  : retrofit2.Callback<List<UserFollowersResponseItem>>{

            override fun onResponse(
                call: Call<List<UserFollowersResponseItem>>,
                response: Response<List<UserFollowersResponseItem>>
            ) {
                _isloading.value = false
                if(response.isSuccessful){
                    _followersList.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<UserFollowersResponseItem>>, t: Throwable) {
                _isloading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}