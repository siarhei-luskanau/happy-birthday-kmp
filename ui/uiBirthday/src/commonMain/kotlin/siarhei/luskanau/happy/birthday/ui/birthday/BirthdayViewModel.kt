package siarhei.luskanau.happy.birthday.ui.birthday

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided
import siarhei.luskanau.happy.birthday.core.network.AnniversaryData
import siarhei.luskanau.happy.birthday.core.network.ServerApiService
import siarhei.luskanau.happy.birthday.domain.birthday.BirthdayDomainService

@Factory
class BirthdayViewModel(
    @Provided private val serverApiService: ServerApiService,
    @Provided private val birthdayDomainService: BirthdayDomainService
) : ViewModel() {

    private val selectedImageFlow = MutableStateFlow<ImageBitmap?>(null)
    private val anniversaryDataFlow = MutableStateFlow<AnniversaryData?>(null)

    @OptIn(ExperimentalTime::class)
    val viewState: StateFlow<BirthdayViewState?> =
        selectedImageFlow.combine(anniversaryDataFlow) { imageBitmap, anniversaryData ->
            val anniversary = anniversaryData?.let {
                try {
                    birthdayDomainService.calculateAnniversary(
                        birthday = it.dob,
                        today = Clock.System.now().toEpochMilliseconds()
                    )
                } catch (error: Throwable) {
                    error.printStackTrace()
                    null
                }
            }
            anniversary?.let {
                BirthdayViewState(
                    name = anniversaryData.name,
                    anniversary = anniversary,
                    theme = anniversaryData.theme,
                    imageBitmap = imageBitmap
                )
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )

    init {
        viewModelScope.launch {
            serverApiService.listenAnniversaryData {
                viewModelScope.launch {
                    anniversaryDataFlow.emit(it)
                }
            }
        }
    }

    fun onEvent(event: BirthdayViewEvent) {
        when (event) {
            is BirthdayViewEvent.SelectedImage -> viewModelScope.launch {
                selectedImageFlow.emit(event.image)
            }
            is BirthdayViewEvent.SelectedImageFile -> viewModelScope.launch {
                selectedImageFlow.emit(event.sharedImage.toImageBitmap())
            }
        }
    }
}
