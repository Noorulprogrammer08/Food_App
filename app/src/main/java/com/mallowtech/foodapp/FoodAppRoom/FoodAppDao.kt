package com.mallowtech.foodapp.FoodAppRoom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FoodAppDao {
    @Insert
    suspend fun insertFoodApp(foodDataList: List<com.mallowtech.foodapp.model.Result>)

    @Query("SELECT * FROM foodstable")
    suspend fun getAllFoodList(): List<com.mallowtech.foodapp.model.Result>

    @Query("SELECT * FROM foodstable ORDER BY name ASC")
    fun getPersonsSortByAscLastName(): LiveData<List<com.mallowtech.foodapp.model.Result?>?>

    @Query("SELECT * FROM foodstable ORDER BY name DESC")
    fun getPersonsSortByDescLastName(): LiveData<List<com.mallowtech.foodapp.model.Result?>?>

    @Query("DELETE FROM foodstable")
    fun clearDatabase()
}