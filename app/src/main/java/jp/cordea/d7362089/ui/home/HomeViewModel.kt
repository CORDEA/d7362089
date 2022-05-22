package jp.cordea.d7362089.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.cordea.d7362089.api.response.PhotoResponse
import jp.cordea.d7362089.usecase.GetRandomLandscapePhotoUseCase
import jp.cordea.d7362089.usecase.SearchPhotosUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchPhotosUseCase: SearchPhotosUseCase,
    private val getRandomLandscapePhotoUseCase: GetRandomLandscapePhotoUseCase,
) : ViewModel() {

    private val _pickupThumbnail = MutableLiveData("")
    val pickupThumbnail: LiveData<String> get() = _pickupThumbnail

    private val _items = MutableLiveData<Map<HomeSection, List<HomeItemViewModel>>>()
    val items: LiveData<Map<HomeSection, List<HomeItemViewModel>>> get() = _items

    private lateinit var pickupPhoto: PhotoResponse
    private lateinit var photos: List<PhotoResponse>

    init {
        viewModelScope.launch { refresh() }
    }

    private suspend fun refresh() {
        val sections = HomeSection.values()
        val photo: PhotoResponse
        try {
            val index = Random.nextInt(sections.size)
            photo = getRandomLandscapePhotoUseCase.execute(sections[index].toString())
        } catch (e: HttpException) {
            e.printStackTrace()
            return
        }
        val items = sections
            .associateWith {
                try {
                    searchPhotosUseCase.execute(it.toString(), 0, 5).results
                } catch (e: HttpException) {
                    e.printStackTrace()
                    emptyList()
                }
            }

        pickupPhoto = photo
        _pickupThumbnail.value = photo.urls.regular
        photos = items.flatMap { it.value }
        _items.value = items.mapValues { entry ->
            entry.value.map {
                HomeItemViewModel(
                    it.id,
                    it.description,
                    it.urls.regular
                )
            }
        }
    }
}

enum class HomeSection {
    CAT,
    DOG,
    FOX,
    RABBIT,
    HAMSTER;

    override fun toString() =
        super.toString().replaceFirstChar { it.uppercase() }
}

data class HomeItemViewModel(
    val id: String,
    val title: String,
    val thumbnail: String
)
