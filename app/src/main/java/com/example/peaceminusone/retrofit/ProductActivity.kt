package com.example.peaceminusone.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peaceminusone.R
import com.example.peaceminusone.retrofit.API.ApiService
import kotlinx.android.synthetic.main.activity_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    private val TAG: String = "ProductActivity"

    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = "Peaceminusone"
        setupRecyclerView()
        getDataFromApi()
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(arrayListOf(), object : ProductAdapter.OnAdapterListener {
            override fun onClick(result: ProductModel.Result) {
                startActivity(
                    Intent(this@ProductActivity, DetailProduct::class.java)
                        .putExtra("intent_title", result.title)
                        .putExtra("intent_image", result.image)
                )
            }
        })
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }

    private fun getDataFromApi() {
        showLoading(true)
        ApiService.endpoint.data()
            .enqueue(object : Callback<ProductModel> {
                override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                    printLog(t.toString())
                    showLoading(false)
                }

                override fun onResponse(
                    call: Call<ProductModel>,
                    response: Response<ProductModel>
                ) {
                    showLoading(false)
                    if (response.isSuccessful) {
                        showResult(response.body()!!)
                    }
                }
            })
    }

    private fun printLog(message: String) {
        Log.d(TAG, message)
    }

    private fun showLoading(loading: Boolean) {
        when (loading) {
            true -> progressBar.visibility = View.VISIBLE
            false -> progressBar.visibility = View.GONE
        }
    }

    private fun showResult(results: ProductModel) {
        for (result in results.result) printLog("title: ${result.title}")
        productAdapter.setData(results.result)
    }
}