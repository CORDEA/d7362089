package jp.cordea.d7362089.ui.details

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.cordea.d7362089.ui.Destination
import jp.cordea.d7362089.ui.home.HomeItemViewModel
import jp.cordea.d7362089.ui.home.HomeSection
import jp.cordea.d7362089.usecase.GetPhotoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPhotoUseCase: GetPhotoUseCase
) : ViewModel() {
    private val id = requireNotNull(savedStateHandle.get<String>(Destination.Details.KEY))

    private val _thumbnail = MutableLiveData("")
    val thumbnail: LiveData<String> get() = _thumbnail

    private val _description = MutableLiveData("")
    val description: LiveData<String> get() = _description

    private val _userName = MutableLiveData("")
    val userName: LiveData<String> get() = _userName

    init {
        viewModelScope.launch {
            val photo = getPhotoUseCase.execute(id, forceRefresh = false)
            _thumbnail.value = photo.urls.regular
            _description.value = photo.description ?: photo.altDescription
            _userName.value = photo.user.name
        }
    }
}
