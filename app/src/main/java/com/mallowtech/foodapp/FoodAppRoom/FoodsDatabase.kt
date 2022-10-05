package com.mallowtech.foodapp.FoodAppRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mallowtech.foodapp.model.FoodDataList


@Database(entities = [com.mallowtech.foodapp.model.Result::class], version = 1)
abstract class FoodsDatabase : RoomDatabase() {
    abstract fun foodAppDao(): FoodAppDao
    companion object{
        private var INSTANCE : FoodsDatabase? = null
        
        fun getDatabase(context: Context):FoodsDatabase{

            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    FoodsDatabase::class.java,
                    "foodAppDB"
                ).build()
            }
            return INSTANCE!!
        }
    }
}