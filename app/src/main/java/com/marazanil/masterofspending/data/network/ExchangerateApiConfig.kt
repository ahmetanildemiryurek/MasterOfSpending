package com.marazanil.masterofspending.data.network


import com.marazanil.masterofspending.data.db.service.ExchangeRateService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ExchangerateApiConfig {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://v6.exchangerate-api.com/v6/c593e141adcac12b2aabb66b/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: ExchangeRateService = retrofit.create(ExchangeRateService::class.java)
}


