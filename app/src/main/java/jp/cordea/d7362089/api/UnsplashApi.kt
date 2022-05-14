package jp.cordea.d7362089.api

import jp.cordea.d7362089.api.response.PhotoResponse
import jp.cordea.d7362089.api.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("/photos/random")
    suspend fun findRandomPhotos(
        @Query("count") count: Int,
        @Query("query") query: String,
        @Query("orientation") orientation: String
    ): List<PhotoResponse>

    @GET("/search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("orientation") orientation: String
    ): SearchResponse
}
