package com.martha.user.base

import android.content.Context
import android.util.Log
import com.martha.user.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource (context: Context){
    fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(OkHttpClient.Builder().also { client ->
                client.callTimeout(60, TimeUnit.SECONDS)
                if (BuildConfig.DEBUG){
                    val logging = HttpLoggingInterceptor()
                    Log.e("lampiran",logging.toString())
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}