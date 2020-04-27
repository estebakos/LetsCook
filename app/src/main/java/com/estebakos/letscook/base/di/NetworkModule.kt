package com.estebakos.letscook.base.di

import com.estebakos.letscook.BuildConfig
import com.estebakos.letscook.base.Constants
import com.estebakos.letscook.data.remote.api.RecipesApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @JvmStatic
    internal fun providesLoggingInterceptor(): HttpLoggingInterceptor? =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        } else null

    @Provides
    @JvmStatic
    internal fun providesOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor?
    ): OkHttpClient.Builder =
        OkHttpClient.Builder().apply {
            loggingInterceptor?.also {
                addInterceptor(it)
            }
        }

    @Provides
    @JvmStatic
    internal fun providesIntegratorOkHttpClient(
        builder: OkHttpClient.Builder
    ): OkHttpClient = builder.build()

    @Provides
    @Singleton
    @JvmStatic
    internal fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @JvmStatic
    internal fun providesErpIntegratorApi(retrofit: Retrofit): RecipesApi =
        retrofit.create(RecipesApi::class.java)

}