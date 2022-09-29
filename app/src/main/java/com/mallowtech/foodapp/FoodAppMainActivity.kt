package com.mallowtech.foodapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.mallowtech.foodapp.adaper.FoodListAdapter
import com.mallowtech.foodapp.databinding.ActivityMainBinding
import com.mallowtech.foodapp.viewModel.FoodAppViewModel

class FoodAppMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewAdapter: FoodListAdapter
    private var flag = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
    }

   /* fun setListener() {
        binding?.ivAssending?.setOnClickListener {
            if (flag == 0) {
                binding?.ivAssending?.setImageResource(R.drawable.ic_decending)
                flag = 1
            } else if (flag == 1) {
                binding.ivAssending.setImageResource(R.drawable.ic_decending)
                flag = 0
            }
        }
    }*/

    private fun initRecyclerView() {
        binding.rvFoodlists.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = FoodListAdapter(this)
        binding.rvFoodlists.adapter = recyclerViewAdapter
    }
    private fun initViewModel(){
        val viewModel:FoodAppViewModel = ViewModelProvider(this).get(FoodAppViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if (it != null){
                recyclerViewAdapter.setFoodList(it)
                recyclerViewAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall()
    }
}