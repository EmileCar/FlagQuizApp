package com.example.flagquizapp.network

import com.example.flagquizapp.models.Country
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


private const val BASE_URL = "https://restcountries.com/v3.1/"

    /**
    * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
    * full Kotlin compatibility.
    */
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    /**
     * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
     * object.
     */
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()


    interface CountryAPIService {
        /**
         * Returns a Coroutine [List] of [MarsProperty] which can be fetched in a Coroutine scope.
         * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
         * HTTP method
         */
        @GET("name/{countryname}")
        suspend fun getCountryData(@Path("countryname") countryName: String): List<Country>

        @GET("all")
        suspend fun getCountries(): List<Country>
    }

    /**
     * A public Api object that exposes the lazy-initialized Retrofit service
     */
    object CountryAPI {
        val retrofitService : CountryAPIService by lazy { retrofit.create(CountryAPIService::class.java) }
    }