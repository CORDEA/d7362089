package jp.cordea.d7362089.usecase

import dagger.Reusable
import jp.cordea.d7362089.repository.PhotoRepository
import javax.inject.Inject

@Reusable
class GetRandomLandscapePhotoUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend fun execute(
        query: String,
    ) = repository.findAtRandom(1, query, "landscape").first()
}
