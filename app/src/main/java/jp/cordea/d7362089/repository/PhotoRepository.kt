package jp.cordea.d7362089.repository

import jp.cordea.d7362089.api.UnsplashApi
import jp.cordea.d7362089.api.response.PhotoResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(
    private val api: UnsplashApi
) {
    private val cache = mutableMapOf<String, PhotoResponse>()

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
    ): List<PhotoResponse> {
        val photos = api.findRandomPhotos(count, query, orientation)
        photos.forEach { cache[it.id] = it }
        return photos
    }

    suspend fun find(
        id: String,
        forceRefresh: Boolean,
    ): PhotoResponse {
        if (cache.containsKey(id) && !forceRefresh) {
            return cache.getValue(id)
        }
        val photo = api.findPhoto(id)
        cache[photo.id] = photo
        return photo
    }
}
