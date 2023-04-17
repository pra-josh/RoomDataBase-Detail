package com.pra.roomdatabase.api

import com.pra.roomdatabase.model.Products
import retrofit2.Call
import retrofit2.http.GET

interface WebApi {

    @GET("/products")
    fun getProductUser(): Call<List<Products>>

}