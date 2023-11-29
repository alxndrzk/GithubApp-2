package com.example.githubapp.ui

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.data.response.UserFollowingResponseItem
import com.example.githubapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class FollowingViewModel: ViewModel() {

    private  val _followingList = MutableLiveData<List<UserFollowingResponseItem>>()
    val followingList : LiveData<List<UserFollowingResponseItem>> = _followingList

    private val _isloading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isloading

//    init {
//        fetchUsernameFollowingData("gizipp")
//    }


    fun fetchUsernameFollowingData(data: String){
        _isloading.value = true
        val client = ApiConfig.getApiService().getUserFollowing(data)
        client.enqueue(object  : retrofit2.Callback<List<UserFollowingResponseItem>>{

            override fun onResponse(
                call: Call<List<UserFollowingResponseItem>>,
                response: Response<List<UserFollowingResponseItem>>
            ) {
                _isloading.value = false
                if(response.isSuccessful){
                    _followingList.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<UserFollowingResponseItem>>, t: Throwable) {
                _isloading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }




}