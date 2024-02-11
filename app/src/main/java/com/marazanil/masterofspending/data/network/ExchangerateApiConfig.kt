package com.marazanil.masterofspending.data.network

import com.marazanil.masterofspending.model.EURCurrency
import com.marazanil.masterofspending.model.GBPCurrency
import com.marazanil.masterofspending.model.USDCurrency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

// Örnek API isteği: https://v6.exchangerate-api.com/v6/c593e141adcac12b2aabb66b/latest/USD


interface ExchangerateApiConfig {


    @GET("v6/c593e141adcac12b2aabb66b/latest/{currency}")
    suspend fun getUSD(@QueryMap queries: Map<String, String>): Response<USDCurrency>

    @GET("v6/c593e141adcac12b2aabb66b/latest/{currency}")
    suspend fun getEURO(@QueryMap queries: Map<String, String>): Response<EURCurrency>

    @GET("v6/c593e141adcac12b2aabb66b/latest/{currency}")
    suspend fun getGBP(@QueryMap queries: Map<String, String>): Response<GBPCurrency>

}