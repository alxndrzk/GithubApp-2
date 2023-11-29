package com.example.githubapp.data.retrofit

import com.example.githubapp.data.response.DetailUserResponse
import com.example.githubapp.data.response.UserFollowersResponseItem
import com.example.githubapp.data.response.UserFollowingResponseItem
import com.example.githubapp.data.response.UserGithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(@Query("q") query: String
    ): Call<UserGithubResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(@Path("username") username : String
    ): Call<DetailUserResponse>

    @Headers("Authorization: token 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")
    @GET("/users/{username}/followers")
    fun getUserFollowers(@Path("username") username : String
    ): Call<List<UserFollowersResponseItem>>

    @Headers("Authorization: token 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")
    @GET("/users/{username}/following")
    fun getUserFollowing(@Path("username") username: String
    ): Call<List<UserFollowingResponseItem>>
}