package jp.cordea.d7362089.usecase

import dagger.Reusable
import jp.cordea.d7362089.repository.PhotoRepository
import javax.inject.Inject

@Reusable
class SearchPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend fun execute(
        query: String,
        page: Int,
        perPage: Int
    ) = repository.search(query, page, perPage, null)
}
