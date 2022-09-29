package com.mallowtech.foodapp.adaper

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mallowtech.foodapp.FoodAppMainActivity
import com.mallowtech.foodapp.R
import com.mallowtech.foodapp.model.FoodList

class FoodListAdapter(val activity: Activity) :
    RecyclerView.Adapter<FoodListAdapter.MyViewHolder>() {
    private var foodList: List<FoodList>? = null
    fun setFoodList(foodList: List<FoodList>?) {
        this.foodList = foodList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodListAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.food_card_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodListAdapter.MyViewHolder, position: Int) {
        holder.run { bind(foodList?.get(position)!!,activity) }
    }

    override fun getItemCount(): Int {
        if (foodList == null) return 0
        else return foodList?.size!!
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = itemView.findViewById(R.id.rv_foodlists)
        val textName: TextView = itemView.findViewById(R.id.tv_Food_name)
        val textDescription: TextView = itemView.findViewById(R.id.tv_food_title)


        fun bind(data: FoodList, activity: Activity) {
            textName.text = data.name + "(" + data.id + ")"
            textDescription.text = "content:" + data.content
         /*   GlideTo.get(activity, Uri.parse(data.image), productImage)*/
        }
    }
}