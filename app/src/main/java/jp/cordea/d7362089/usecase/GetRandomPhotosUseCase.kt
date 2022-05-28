package jp.cordea.d7362089.usecase

import dagger.Reusable
import jp.cordea.d7362089.repository.PhotoRepository
import javax.inject.Inject

@Reusable
class GetRandomPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend fun execute(
        query: String,
        count: Int,
    ) = repository.findAtRandom(count, query, null)
}
