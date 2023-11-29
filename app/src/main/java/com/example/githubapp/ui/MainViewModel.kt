package com.example.githubapp.ui

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.data.response.ItemsItem
import com.example.githubapp.data.response.UserGithubResponse
import com.example.githubapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel(){

    private  val _listUsername = MutableLiveData<List<ItemsItem>>()
    val listReview: LiveData<List<ItemsItem>> = _listUsername

    private val _isloading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isloading


    fun findUsernameAccount(data: String){
        _isloading.value = true
        val client = ApiConfig.getApiService().getUser(data)
        client.enqueue(object  : retrofit2.Callback<UserGithubResponse>{
            override fun onResponse(
                call: Call<UserGithubResponse>,
                response: Response<UserGithubResponse>
            ){
                _isloading.value = false
                if(response.isSuccessful){
                    _listUsername.value = response.body()?.items

                }else{
                    Log.e(ContentValues.TAG,"onFilure ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserGithubResponse>, t: Throwable){
                _isloading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }


}