package com.example.peaceminusone.retrofit

data class ProductModel (
    val result: ArrayList<Result>
) {
    data class Result (val id: Int, val title: String, val image: String)
}