package com.hegunhee.maplemfinder.core.data

import com.hegunhee.maplemfinder.core.data.api.MapleMApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal fun getMapleMMoshi() : Moshi {
    return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
}

internal fun getMapleMApi(moshi : Moshi = getMapleMMoshi()) : MapleMApi =
    Retrofit.Builder()
        .baseUrl(BuildConfig.MapleMBaseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(provideOkHttpClient(NexonInterceptor()))
        .build()
        .create(MapleMApi::class.java)


private fun provideOkHttpClient(vararg interceptor: Interceptor) : OkHttpClient =
    OkHttpClient.Builder().run {
        interceptor.forEach { addInterceptor(it) }
        build()
    }

private class NexonInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("x-nxopen-api-key", BuildConfig.API_KEY)
                .build()
            proceed(newRequest)
        }
    }
}