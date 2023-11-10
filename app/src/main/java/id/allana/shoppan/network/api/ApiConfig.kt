package id.allana.shoppan.network.api

import id.allana.shoppan.network.service.AuthApiService
import id.allana.shoppan.network.service.ProductApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private const val BASE_URL = "http://13.229.95.72:3300/"

        fun getAuthApiService(): AuthApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(AuthApiService::class.java)
        }

        fun getProductApiService(authToken: String): ProductApiService {
            val productInterceptor = Interceptor {
                val requestBuilder = it.request().newBuilder()
                requestBuilder.addHeader("Authorization", "Bearer $authToken")
                it.proceed(requestBuilder.build())
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(productInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ProductApiService::class.java)
        }
    }
}