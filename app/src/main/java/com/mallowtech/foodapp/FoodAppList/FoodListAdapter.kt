package com.mallowtech.foodapp.FoodAppList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mallowtech.foodapp.databinding.FoodCardListBinding


class FoodListAdapter(private var foodList: List<com.mallowtech.foodapp.model.Result>) :
    RecyclerView.Adapter<FoodListAdapter.MyViewHolder>() {

    val filter: String? = null

    fun setFoodList(addFoodList: List<com.mallowtech.foodapp.model.Result>) {
        foodList = addFoodList
        notifyDataSetChanged()
    }

    fun clear(clearFoodList: List<com.mallowtech.foodapp.model.Result>) {
        foodList = clearFoodList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view =
            FoodCardListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvFoodName.text = foodList[position].name
        holder.binding.tvFoodDescription.text = foodList[position].content
        Glide.with(holder.itemView.context).load(foodList[position].image)
            .apply(RequestOptions().centerCrop())
            .into(holder.binding.ivFoodProduct)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    class MyViewHolder(val binding: FoodCardListBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}