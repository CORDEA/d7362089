package jp.cordea.d7362089.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.cordea.d7362089.api.response.PhotoResponse
import jp.cordea.d7362089.usecase.GetRandomLandscapePhotoUseCase
import jp.cordea.d7362089.usecase.GetRandomPhotosUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomPhotosUseCase: GetRandomPhotosUseCase,
    private val getRandomLandscapePhotoUseCase: GetRandomLandscapePhotoUseCase,
) : ViewModel() {
    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

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
                    getRandomPhotosUseCase.execute(it.toString(), 5)
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
                    it.description ?: it.altDescription.orEmpty(),
                    it.urls.regular
                )
            }
        }
    }

    fun onPickupClicked() {
        viewModelScope.launch {
            _event.emit(HomeEvent.ToDetails(pickupPhoto))
        }
    }

    fun onItemClicked(id: String) {
        viewModelScope.launch {
            _event.emit(HomeEvent.ToDetails(photos.first { it.id == id }))
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

sealed class HomeEvent {
    class ToDetails(val response: PhotoResponse) : HomeEvent()
}
