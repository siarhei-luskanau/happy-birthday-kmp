package siarhei.luskanau.happy.birthday.ui.birthday

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided
import siarhei.luskanau.happy.birthday.core.network.ServerApiService
import siarhei.luskanau.happy.birthday.domain.birthday.BirthdayDomainService

@Factory
internal class BirthdayViewModelImpl(
    @Provided private val serverApiService: ServerApiService,
    @Provided private val birthdayDomainService: BirthdayDomainService
) : BirthdayViewModel() {

    private val selectedImageFlow = MutableStateFlow<ImageBitmap?>(null)

    override val viewState: StateFlow<BirthdayViewState?> =
        serverApiService.openWebSocket()
            .combine(selectedImageFlow) { anniversaryData, imageBitmap ->
                anniversaryData?.let {
                    BirthdayViewState(
                        name = it.name,
                        anniversary = birthdayDomainService.calculateAnniversary(
                            birthday = it.dob,
                            today = Clock.System.now().toEpochMilliseconds()
                        ),
                        theme = it.theme,
                        imageBitmap = imageBitmap
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )

    override fun updateSelectedImage(selectedImage: ImageBitmap) {
        viewModelScope.launch {
            selectedImageFlow.emit(selectedImage)
        }
    }
}
