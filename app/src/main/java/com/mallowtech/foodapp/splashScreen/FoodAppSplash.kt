package com.mallowtech.foodapp.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.mallowtech.foodapp.FoodAppList.FoodAppMainActivity
import com.mallowtech.foodapp.R

class FoodAppSplash : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        val splashscreen = findViewById<ImageView>(R.id.iv_food_App)
        splashscreen.alpha = 0f
        splashscreen.animate().setDuration(1000).alpha(1f).withEndAction {
            val intent = Intent(this@FoodAppSplash, FoodAppMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}