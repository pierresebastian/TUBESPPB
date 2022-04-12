package com.example.peaceminusone.retrofit.API

import com.example.peaceminusone.retrofit.ProductModel
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {

    @GET("data.php")
    fun data(): Call<ProductModel>
}