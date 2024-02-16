package com.marazanil.masterofspending.data.db.service

import com.marazanil.masterofspending.model.ExchangeRateResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ExchangeRateService {
    @GET
    fun getLatestRates(@Url url: String): Call<ExchangeRateResponse>
}

