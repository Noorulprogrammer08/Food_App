package com.mallowtech.foodapp.model

data class FoodList(
    val content: String,
   /* val dataPoints: List<Any>,*/
    val id: Int,
    val image: String,
    val images: List<Any>,
    val link: String,
    val name: String,
  /*  val relevance: Double,*/
    /*val type: String*/
)