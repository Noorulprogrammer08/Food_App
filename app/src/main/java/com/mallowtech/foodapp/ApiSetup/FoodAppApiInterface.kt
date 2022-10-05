package com.mallowtech.foodapp.ApiSetup

import com.mallowtech.foodapp.model.FoodDataList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface FoodAppApiInterface {

    @GET("food/search")
    fun getFoodsList(
        @Query("apiKey") apiKey:String = "f7aab39956434f1d8aa320f3aa01920a",
        @Query("query") query:String = "apple",
        @Query("number") number: Int = 10
    ): Call<FoodDataList>
}