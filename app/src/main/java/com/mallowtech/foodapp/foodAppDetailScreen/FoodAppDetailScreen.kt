package com.mallowtech.foodapp.foodAppDetailScreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mallowtech.foodapp.FoodAppList.FoodAppMainActivity
import com.mallowtech.foodapp.databinding.FoodDescriptionPreviewBinding

class FoodAppDetailScreen(private var foodList: List<com.mallowtech.foodapp.model.Result>) :
    AppCompatActivity() {
    private lateinit var binding: FoodDescriptionPreviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoodDescriptionPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListerner()
        init()

    }

    private fun setListerner() {
        binding.ivFoodProductPreviewArrow.setOnClickListener {
            val intent = Intent(this@FoodAppDetailScreen, FoodAppMainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.ivFoodProductPreviewLink.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            intent.data = Uri.parse(foodList[0].link)
            startActivity(intent)
        }

    }

    private fun init() {
        binding.tvFoodTitle.text = foodList[0].name
        binding.tvFoodContentTitle.text = foodList[0].name
        binding.tvFoodDetail.text = foodList[0].content
        Glide.with(binding.ivFoodProductPreview)
            .load(foodList[0].image)
            .centerCrop()
            .into(binding.ivFoodProductPreview)
    }
}