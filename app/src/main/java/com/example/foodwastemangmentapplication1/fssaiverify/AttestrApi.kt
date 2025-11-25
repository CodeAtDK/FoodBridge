package com.example.foodwastemangmentapplication1.fssaiverify

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AttestrApi {
    @Headers("Content-Type: application/json")
    @POST("/v1/verification/fssai") // adapt to provider's path
    suspend fun verifyFssai(@Body body: VerifyRequest): VerifyResponse

    companion object {
        fun create(baseUrl: String, apiKey: String): AttestrApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $apiKey")
                        .build()
                    chain.proceed(request)
                }
                .addInterceptor(logger)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(AttestrApi::class.java)
        }
    }
}