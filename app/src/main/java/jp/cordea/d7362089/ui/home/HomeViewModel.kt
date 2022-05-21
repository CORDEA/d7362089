package jp.cordea.d7362089.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.cordea.d7362089.usecase.GetRandomLandscapePhotoUseCase
import jp.cordea.d7362089.usecase.SearchPhotosUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchPhotosUseCase: SearchPhotosUseCase,
    private val getRandomLandscapePhotoUseCase: GetRandomLandscapePhotoUseCase,
) : ViewModel() {

    private val _pickupThumbnail = MutableLiveData("")
    val pickupThumbnail: LiveData<String> get() = _pickupThumbnail

    private val _items = MutableLiveData<Map<HomeSection, List<HomeItemViewModel>>>()
    val items: LiveData<Map<HomeSection, List<HomeItemViewModel>>> get() = _items
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

class HomeItemViewModel(
    val id: String,
    val title: String,
    val thumbnail: String
)
