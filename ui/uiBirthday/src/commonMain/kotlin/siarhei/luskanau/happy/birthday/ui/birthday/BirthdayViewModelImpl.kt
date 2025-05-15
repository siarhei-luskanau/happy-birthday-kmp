package siarhei.luskanau.happy.birthday.ui.birthday

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

    override val viewState: StateFlow<BirthdayViewState?> =
        serverApiService.openWebSocket()
            .map { anniversaryData ->
                anniversaryData?.let {
                    BirthdayViewState(
                        name = it.name,
                        anniversary = birthdayDomainService.calculateAnniversary(
                            birthday = it.dob,
                            today = Clock.System.now().toEpochMilliseconds()
                        ),
                        theme = it.theme
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )
}
