
package com.mallowtech.foodapp.common

import android.app.Application
import android.content.Context
import java.io.Closeable

class MyApplication: Application() {
    lateinit var foodAppRepo: FoodAppRepository
    companion object {
        private lateinit var myInstance: MyApplication
        fun getContext(): Context? {
            return myInstance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        myInstance = this
    }
}
