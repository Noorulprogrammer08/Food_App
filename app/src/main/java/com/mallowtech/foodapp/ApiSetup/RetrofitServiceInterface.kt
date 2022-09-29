package com.mallowtech.foodapp.ApiSetup

import com.mallowtech.foodapp.model.FoodList
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServiceInterface {

    @GET("search")
    fun getFoodsList(): Call<List<FoodList>>
}