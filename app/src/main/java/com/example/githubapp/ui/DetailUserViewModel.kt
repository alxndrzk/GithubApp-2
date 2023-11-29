package com.example.githubapp.ui

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.data.response.DetailUserResponse
import com.example.githubapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class DetailUserViewModel: ViewModel() {



    private val _detailUserView = MutableLiveData<DetailUserResponse>()
    val detailuserview : LiveData<DetailUserResponse> = _detailUserView

    private val _isloading = MutableLiveData<Boolean>()
    val isloading: LiveData<Boolean> = _isloading


    fun findDetailUsernameAccount(data:String){
        _isloading.value = true
        val client = ApiConfig.getApiService().getDetailUser(data)
        client.enqueue(object : retrofit2.Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ){
                _isloading.value = false
                if(response.isSuccessful){
                    _detailUserView.value = response.body()
                }else{
                    Log.e(ContentValues.TAG,"onFilure ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isloading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}