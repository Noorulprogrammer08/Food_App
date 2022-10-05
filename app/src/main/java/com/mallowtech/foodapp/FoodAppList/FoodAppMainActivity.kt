package com.mallowtech.foodapp.FoodAppList

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mallowtech.foodapp.ApiSetup.ApiManager
import com.mallowtech.foodapp.R
import com.mallowtech.foodapp.common.FoodAppRepository
import com.mallowtech.foodapp.databinding.FoodAppMainBinding
import com.mallowtech.foodapp.databinding.FoodCardListBinding
import com.mallowtech.foodapp.model.FoodDataList
import com.mallowtech.foodapp.model.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
class FoodAppMainActivity : AppCompatActivity() {
    private lateinit var binding: FoodAppMainBinding
    private lateinit var cardBinding: FoodCardListBinding
    var recyclerViewAdapter = FoodListAdapter(listOf())
    var filterSearchList: List<Result> = listOf()
    private val foodAppViewModel: FoodAppViewModel by lazy { ViewModelProvider(this)[foodAppViewModel::class.java] }
    private var flag = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FoodAppMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFoodlists.layoutManager = LinearLayoutManager(this)
        binding.rvFoodlists.adapter = recyclerViewAdapter
        binding.rvFoodlists.setHasFixedSize(true)

        setListener()
        makeAPICall()
        getSearchView()
        refreshPage()
        observeViewModel()

    }

    private fun setListener() {
        /* cardBinding.clFoodCardContainer.setOnClickListener{
             val intent = Intent(this@FoodAppMainActivity,FoodAppDetailScreen::class.java)
             startActivity(intent)
         }*/
        binding.ivAssending.setOnClickListener {
            if (flag == 0) {
                binding.ivAssending.setImageResource(R.drawable.ic_decending)
                foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity) {
                    recyclerViewAdapter.setFoodList(it as List<Result>)
                }
                flag = 1
            } else if (flag == 1) {
                binding.ivAssending.setImageResource(R.drawable.ic_ascending)
                foodAppViewModel.loadAsc().observe(this@FoodAppMainActivity) {
                    recyclerViewAdapter.setFoodList(it as List<Result>)
                }
                flag = 0
            }
        }
        getRadioButtons()

    }

    private fun getRadioButtons() {
        binding.ivFilter.setOnClickListener {
            binding.rgFilterbox.isVisible = true
        }

        binding.rbOrange.setOnClickListener {
            GlobalScope.launch {
                FoodAppRepository.foodsDatabase?.foodAppDao()?.clearDatabase()
                foodAppViewModel.getFoodDataBase("orange", 1)
            }
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity, Observer {
                recyclerViewAdapter.setFoodList(it as List<Result>)
            })
            Toast.makeText(this@FoodAppMainActivity, "orange", Toast.LENGTH_SHORT).show()
            binding.rbOrange.isVisible = false
        }

        binding.rbPineapple.setOnClickListener {

            GlobalScope.launch {
                FoodAppRepository.foodsDatabase?.foodAppDao()?.clearDatabase()
                foodAppViewModel.getFoodDataBase("pineapple", 1)
            }
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity, Observer {
                recyclerViewAdapter.setFoodList(it as List<Result>)
            })
            Toast.makeText(this@FoodAppMainActivity, "pineapple", Toast.LENGTH_SHORT).show()
            binding.rbPineapple.isVisible = false
        }

        binding.rbWatermelon.setOnClickListener {

            GlobalScope.launch {
                FoodAppRepository.foodsDatabase?.foodAppDao()?.clearDatabase()
                foodAppViewModel.getFoodDataBase("WaterMelon", 1)
            }
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity, Observer {
                recyclerViewAdapter.setFoodList(it as List<Result>)
            })
            Toast.makeText(this@FoodAppMainActivity, "WaterMelon", Toast.LENGTH_SHORT).show()
            binding.rbWatermelon.isVisible = false
        }

        binding.rbJackFruit.setOnClickListener {
            GlobalScope.launch {
                FoodAppRepository.foodsDatabase?.foodAppDao()?.clearDatabase()
            }
            foodAppViewModel.getFoodDataBase("JackFruit", 1)
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity, Observer {
                recyclerViewAdapter.setFoodList(it as List<Result>)
            })
            Toast.makeText(this@FoodAppMainActivity, "JackFruit", Toast.LENGTH_SHORT).show()
            binding.rbJackFruit.isVisible = false
        }

        binding.rbGrapes.setOnClickListener {

            GlobalScope.launch {
                FoodAppRepository.foodsDatabase?.foodAppDao()?.clearDatabase()
                foodAppViewModel.getFoodDataBase("Grapes", 1)
            }
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity, Observer {
                recyclerViewAdapter.setFoodList(it as List<Result>)
            })
            Toast.makeText(this@FoodAppMainActivity, "Grapes", Toast.LENGTH_SHORT).show()
            binding.rbGrapes.isVisible = false
        }

        binding.rbBanana.setOnClickListener {

            GlobalScope.launch {
                FoodAppRepository.foodsDatabase?.foodAppDao()?.clearDatabase()
                foodAppViewModel.getFoodDataBase("Banana", 1)
            }
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity, Observer {
                recyclerViewAdapter.setFoodList(it as List<Result>)
            })
            Toast.makeText(this@FoodAppMainActivity, "Banana", Toast.LENGTH_SHORT).show()
            binding.rbBanana.isVisible = false
        }
    }

    private fun getSearchView() {
        binding.svSearch.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchViewList(newText)
                return true
            }
        })
    }

    private fun searchViewList(newText: String?) {
        val filteredList: MutableList<Result> = mutableListOf()
        for (food in filterSearchList) {
            if (newText != null) {
                if (food.name?.lowercase()!!.contains(newText.lowercase())) {
                    filteredList.add(food)
                }
            }
        }
        recyclerViewAdapter.setFoodList(filteredList)
    }

    private fun observeViewModel() {
        with(FoodAppRepository) {
            progressBarBottomShowing.observe(this@FoodAppMainActivity) {
                binding.pbLoadingBottom.isVisible = it
            }
            progressBarCenterShowing.observe(this@FoodAppMainActivity) {
                binding.pbLoadingCenter.isVisible = it
            }
        }
    }

    private fun refreshPage() {
        binding.slRefresh.setOnRefreshListener {
            GlobalScope.launch {
                FoodAppRepository.foodsDatabase?.foodAppDao()
            }
            foodAppViewModel.getFoodDataBase("apple", 10)
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity) {
                recyclerViewAdapter.setFoodList(it as List<Result>)
                filterSearchList = it
            }
            foodAppViewModel.getFoodDataBase("orange", 10)
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity) {
                recyclerViewAdapter.setFoodList(it as List<Result>)
                filterSearchList = it
            }
            foodAppViewModel.getFoodDataBase("waterMelon", 10)
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity) {
                recyclerViewAdapter.setFoodList(it as List<Result>)
                filterSearchList = it
            }
            foodAppViewModel.getFoodDataBase("jackFruit", 10)
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity) {
                recyclerViewAdapter.setFoodList(it as List<Result>)
                filterSearchList = it
            }
            foodAppViewModel.getFoodDataBase("banana", 10)
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity) {
                recyclerViewAdapter.setFoodList(it as List<Result>)
                filterSearchList = it
            }
            foodAppViewModel.getFoodDataBase("pineapple", 10)
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity) {
                recyclerViewAdapter.setFoodList(it as List<Result>)
                filterSearchList = it
            }
            foodAppViewModel.getFoodDataBase("grapes", 10)
            foodAppViewModel.loadDesc().observe(this@FoodAppMainActivity) {
                recyclerViewAdapter.setFoodList(it as List<Result>)
                filterSearchList = it
            }
        }
    }


    private fun makeAPICall() {
        ApiManager.unAuthorisedClient.getFoodsList().enqueue(object : Callback<FoodDataList> {
            override fun onResponse(
                call: Call<FoodDataList>, response: Response<FoodDataList>
            ) {
                Log.d("food results", "food result called response Success")
                if (response.body() != null) {
                    recyclerViewAdapter.setFoodList(response.body()!!.searchResults[0].results)
                } else {
                    Log.d("food results", response.errorBody()!!.toString())
                    Toast.makeText(
                        this@FoodAppMainActivity,
                        response.errorBody()!!.string(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<FoodDataList>, t: Throwable) {
                Log.d("food results", t.toString())
            }

        })
    }
}


