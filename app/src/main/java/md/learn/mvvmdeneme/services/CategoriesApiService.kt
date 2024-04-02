package md.learn.mvvmdeneme.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//https://www.themealdb.com/api/json/v1/1/categories.php
private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create()).build()

interface CategoriesApiService{
    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponsesList

    @GET("search.php?s=")
    suspend fun getCategorieDetail(): CategoriesResponsesList
}

val categoriesApiService = retrofit.create(CategoriesApiService::class.java)
