package jp.cordea.d7362089.repository

import jp.cordea.d7362089.api.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(
    private val api: UnsplashApi
) {
    suspend fun search(
        query: String,
        page: Int,
        perPage: Int,
        orientation: String?
    ) = api.searchPhotos(query, page, perPage, orientation)

    suspend fun findAtRandom(
        count: Int,
        query: String,
        orientation: String?
    ) = api.findRandomPhotos(count, query, orientation)
}
