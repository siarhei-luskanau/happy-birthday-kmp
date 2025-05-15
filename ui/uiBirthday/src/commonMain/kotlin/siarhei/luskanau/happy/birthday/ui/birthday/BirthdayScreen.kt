package siarhei.luskanau.happy.birthday.ui.birthday

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.happy.birthday.core.common.model.Anniversary
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayDescription
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayNumbers
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayTheme
import siarhei.luskanau.happy.birthday.ui.common.resources.Res
import siarhei.luskanau.happy.birthday.ui.common.resources.app_name
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_nanit

@Composable
fun BirthdayScreen(viewModel: BirthdayViewModel) {
    val viewState by viewModel.viewState.collectAsState(null)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = viewState?.let {
                buildString {
                    appendLine(it.name)
                    appendLine(it.anniversary.numbers)
                    appendLine(it.anniversary.description)
                    appendLine(it.theme)
                }
            } ?: stringResource(Res.string.app_name),
            modifier = Modifier.testTag("t_text"),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Image(
            imageVector = vectorResource(Res.drawable.ic_nanit),
            contentDescription = null
        )
    }
}

@Preview
@Composable
internal fun BirthdayScreenFoxPreview() = BirthdayScreen(
    viewModel = previewViewModel(
        viewState = BirthdayViewState(
            name = "title",
            anniversary = Anniversary(
                numbers = listOf(BirthdayNumbers.NUMBER_11),
                description = BirthdayDescription.MONTHS

            ),
            theme = BirthdayTheme.FOX
        )
    )
)

internal fun previewViewModel(viewState: BirthdayViewState): BirthdayViewModel = object : BirthdayViewModel() {
    override val viewState: StateFlow<BirthdayViewState> = MutableStateFlow(viewState)
}
