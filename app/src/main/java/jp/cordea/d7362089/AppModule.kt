package jp.cordea.d7362089

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.cordea.d7362089.api.UnsplashApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor {
                it.proceed(
                    it
                        .request()
                        .newBuilder()
                        .addHeader(
                            "Authorization",
                            "Client-ID ${BuildConfig.ACCESS_KEY}"
                        )
                        .build()
                )
            }
            .build()

    @Provides
    @Singleton
    @ExperimentalSerializationApi
    fun provideRetrofit(client: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.unsplash.com")
            .addConverterFactory(
                json.asConverterFactory(MediaType.parse("application/json")!!)
            )
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): UnsplashApi =
        retrofit.create(UnsplashApi::class.java)
}
