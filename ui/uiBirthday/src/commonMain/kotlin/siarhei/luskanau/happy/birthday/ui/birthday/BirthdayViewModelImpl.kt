package siarhei.luskanau.happy.birthday.ui.birthday

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided
import siarhei.luskanau.happy.birthday.core.network.ServerApiService

@Factory
internal class BirthdayViewModelImpl(@Provided private val serverApiService: ServerApiService) : BirthdayViewModel() {

    override val viewState: StateFlow<BirthdayViewState> =
        serverApiService.openWebSocket()
            .map { BirthdayViewState(value = it?.name) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = BirthdayViewState(value = null)
            )
}
