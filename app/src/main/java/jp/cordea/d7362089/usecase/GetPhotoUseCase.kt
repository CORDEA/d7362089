package jp.cordea.d7362089.usecase

import dagger.Reusable
import jp.cordea.d7362089.repository.PhotoRepository
import javax.inject.Inject

@Reusable
class GetPhotoUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend fun execute(id: String, forceRefresh: Boolean) = repository.find(id, forceRefresh)
}
