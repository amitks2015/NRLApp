package com.test.data.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService {

    companion object {
        fun getEndpoint(): NRLEndpoint {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
            httpClient.readTimeout(30, TimeUnit.SECONDS)
            httpClient.connectTimeout(30, TimeUnit.SECONDS)

            return Retrofit.Builder()
                .baseUrl(URL.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
                .create(NRLEndpoint::class.java)
        }
    }
}