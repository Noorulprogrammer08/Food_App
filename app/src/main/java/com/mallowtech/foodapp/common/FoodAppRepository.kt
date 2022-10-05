package com.mallowtech.foodapp.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mallowtech.foodapp.ApiSetup.ApiManager
import com.mallowtech.foodapp.FoodAppRoom.FoodsDatabase
import com.mallowtech.foodapp.model.FoodDataList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object FoodAppRepository {
    var foodsDatabase: FoodsDatabase? = null
    val progressBarCenterShowing by lazy { SingleLiveEvent<Boolean>() }
    val progressBarBottomShowing by lazy { SingleLiveEvent<Boolean>() }

    init {
        foodsDatabase = MyApplication.getContext()?.let { FoodsDatabase.getDatabase(it) }
    }


    fun getFoodList(name: String, viewModelCallBack: FoodAppViewModelCallBack) {
        if (true) {
            ApiManager.unAuthorisedClient.getFoodsList(query = name)
                .enqueue(object : Callback<FoodDataList?> {
                    override fun onResponse(
                        call: Call<FoodDataList?>,
                        response: Response<FoodDataList?>
                    ) {
                        if (response.isSuccessful)
                            response.body()?.let {
                                GlobalScope.launch {
                                    foodsDatabase?.foodAppDao()
                                        ?.insertFoodApp(it.searchResults[0].results)
                                }
                                progressBarBottomShowing.value = false
                                progressBarCenterShowing.value = false
                            } else {
                            val gson = Gson()
                            val type = object : TypeToken<GetFoodListErrorResponse>() {}.type
                            val errorResponse: GetFoodListErrorResponse =
                                gson.fromJson(response.errorBody()!!.charStream(), type)
                            viewModelCallBack.onError(errorResponse)
                            progressBarBottomShowing.value = false
                            progressBarCenterShowing.value = false
                        }
                    }

                    override fun onFailure(call: Call<FoodDataList?>, t: Throwable) {
                        viewModelCallBack.onFailure(t.message)
                        progressBarBottomShowing.value = false
                        progressBarCenterShowing.value = false
                    }

                })
        } else {
            viewModelCallBack.onNoNetwork("")

        }
    }
}
