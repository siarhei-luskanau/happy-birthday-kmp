package siarhei.luskanau.happy.birthday.ui.birthday

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import network.chaintech.cmpimagepickncrop.CMPImagePickNCropDialog
import network.chaintech.cmpimagepickncrop.imagecropper.rememberImageCropper
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import siarhei.luskanau.happy.birthday.core.common.model.Anniversary
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayDescription
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayNumbers
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayTheme
import siarhei.luskanau.happy.birthday.ui.common.resources.Res
import siarhei.luskanau.happy.birthday.ui.common.resources.bg_birthday_elephant
import siarhei.luskanau.happy.birthday.ui.common.resources.bg_birthday_fox
import siarhei.luskanau.happy.birthday.ui.common.resources.bg_birthday_pelican
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_camera_plus_elephant
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_camera_plus_fox
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_camera_plus_pelican
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_nanit
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_0
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_1
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_10
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_11
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_12
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_2
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_3
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_4
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_5
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_6
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_7
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_8
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_num_9
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_profile_elephant
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_profile_fox
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_profile_pelican
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_swirls_left
import siarhei.luskanau.happy.birthday.ui.common.resources.ic_swirls_right
import siarhei.luskanau.happy.birthday.ui.common.resources.month_old
import siarhei.luskanau.happy.birthday.ui.common.resources.months_old
import siarhei.luskanau.happy.birthday.ui.common.resources.today_is
import siarhei.luskanau.happy.birthday.ui.common.resources.year_old
import siarhei.luskanau.happy.birthday.ui.common.resources.years_old

@Composable
fun BirthdayScreen(viewModel: BirthdayViewModel) {
    val viewState by viewModel.viewState.collectAsState(null)
    val imageCropper = rememberImageCropper()
    var openImagePicker by remember { mutableStateOf(value = false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        CMPImagePickNCropDialog(
            imageCropper = imageCropper,
            openImagePicker = openImagePicker,
            autoZoom = true,
            imagePickerDialogHandler = { openImagePicker = it },
            selectedImageCallback = {
                viewModel.updateSelectedImage(it)
            }
        )

        Spacer(
            modifier = Modifier.fillMaxSize().background(
                color = Color(
                    when (viewState?.theme) {
                        BirthdayTheme.ELEPHANT -> 0xFFFEEFCB
                        BirthdayTheme.FOX -> 0xFFC5E8DF
                        BirthdayTheme.PELICAN -> 0xFFDAF1F6
                        else -> 0xFFFFFFFF
                    }
                )
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.TopCenter)
                .padding(start = 50.dp, end = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            viewState?.let { viewStateNN ->
                Spacer(Modifier.size(20.dp).weight(1f))
                Text(
                    text = stringResource(Res.string.today_is, viewStateNN.name).uppercase(),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color(0xFF394562),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.size(13.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        imageVector = vectorResource(Res.drawable.ic_swirls_left),
                        contentDescription = null
                    )
                    viewStateNN.anniversary.numbers.forEach { number ->
                        Spacer(Modifier.size(22.dp))
                        Image(
                            imageVector = vectorResource(
                                when (number) {
                                    BirthdayNumbers.NUMBER_0 -> Res.drawable.ic_num_0
                                    BirthdayNumbers.NUMBER_1 -> Res.drawable.ic_num_1
                                    BirthdayNumbers.NUMBER_2 -> Res.drawable.ic_num_2
                                    BirthdayNumbers.NUMBER_3 -> Res.drawable.ic_num_3
                                    BirthdayNumbers.NUMBER_4 -> Res.drawable.ic_num_4
                                    BirthdayNumbers.NUMBER_5 -> Res.drawable.ic_num_5
                                    BirthdayNumbers.NUMBER_6 -> Res.drawable.ic_num_6
                                    BirthdayNumbers.NUMBER_7 -> Res.drawable.ic_num_7
                                    BirthdayNumbers.NUMBER_8 -> Res.drawable.ic_num_8
                                    BirthdayNumbers.NUMBER_9 -> Res.drawable.ic_num_9
                                    BirthdayNumbers.NUMBER_10 -> Res.drawable.ic_num_10
                                    BirthdayNumbers.NUMBER_11 -> Res.drawable.ic_num_11
                                    BirthdayNumbers.NUMBER_12 -> Res.drawable.ic_num_12
                                }
                            ),
                            contentDescription = null
                        )
                    }
                    Spacer(Modifier.size(22.dp))
                    Image(
                        imageVector = vectorResource(Res.drawable.ic_swirls_right),
                        contentDescription = null
                    )
                }
                Spacer(Modifier.size(14.dp))
                Text(
                    text = stringResource(
                        when (viewStateNN.anniversary.description) {
                            BirthdayDescription.MONTH -> Res.string.month_old
                            BirthdayDescription.MONTHS -> Res.string.months_old
                            BirthdayDescription.YEAR -> Res.string.year_old
                            BirthdayDescription.YEARS -> Res.string.years_old
                        }
                    ).uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF394562),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.size(15.dp).weight(1f))
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .clickable { openImagePicker = !openImagePicker },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = vectorResource(
                            when (viewStateNN.theme) {
                                BirthdayTheme.ELEPHANT -> Res.drawable.ic_profile_elephant
                                BirthdayTheme.FOX -> Res.drawable.ic_profile_fox
                                BirthdayTheme.PELICAN -> Res.drawable.ic_profile_pelican
                            }
                        ),
                        modifier = Modifier.size(200.dp),
                        contentDescription = null
                    )
                    viewStateNN.imageBitmap?.let { imageBitmap ->
                        Image(
                            bitmap = imageBitmap,
                            modifier = Modifier.size(188.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                    }
                    Image(
                        imageVector = vectorResource(
                            when (viewStateNN.theme) {
                                BirthdayTheme.ELEPHANT -> Res.drawable.ic_camera_plus_elephant
                                BirthdayTheme.FOX -> Res.drawable.ic_camera_plus_fox
                                BirthdayTheme.PELICAN -> Res.drawable.ic_camera_plus_pelican
                            }
                        ),
                        modifier = Modifier.offset(x = 68.dp, y = (-68).dp),
                        contentDescription = null
                    )
                }
                Spacer(Modifier.size(15.dp))
            }
        }
        viewState?.theme?.let { theme ->
            Image(
                painter = painterResource(
                    when (theme) {
                        BirthdayTheme.ELEPHANT -> Res.drawable.bg_birthday_elephant
                        BirthdayTheme.FOX -> Res.drawable.bg_birthday_fox
                        BirthdayTheme.PELICAN -> Res.drawable.bg_birthday_pelican
                    }
                ),
                modifier = Modifier
                    .fillMaxSize(),
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = vectorResource(Res.drawable.ic_nanit),
                modifier = Modifier.testTag("t_nanit"),
                contentDescription = "Nanit Logo"
            )
        }
    }
}

@Preview
@Composable
internal fun BirthdayScreenEmptyPreview() = BirthdayScreen(
    viewModel = previewViewModel(viewState = null)
)

@Preview
@Composable
internal fun BirthdayScreenElephantPreview() = BirthdayScreen(
    viewModel = previewViewModel(
        viewState = BirthdayViewState(
            name = "Cristiano Ronaldo",
            anniversary = Anniversary(
                numbers = listOf(BirthdayNumbers.NUMBER_1),
                description = BirthdayDescription.MONTH

            ),
            theme = BirthdayTheme.ELEPHANT,
            imageBitmap = null
        )
    )
)

@Preview
@Composable
internal fun BirthdayScreenFoxPreview() = BirthdayScreen(
    viewModel = previewViewModel(
        viewState = BirthdayViewState(
            name = "Cristiano Ronaldo",
            anniversary = Anniversary(
                numbers = listOf(BirthdayNumbers.NUMBER_2),
                description = BirthdayDescription.MONTHS

            ),
            theme = BirthdayTheme.FOX,
            imageBitmap = null
        )
    )
)

@Preview
@Composable
internal fun BirthdayScreenPelicanPreview() = BirthdayScreen(
    viewModel = previewViewModel(
        viewState = BirthdayViewState(
            name = "Cristiano Ronaldo",
            anniversary = Anniversary(
                numbers = listOf(BirthdayNumbers.NUMBER_1),
                description = BirthdayDescription.YEAR

            ),
            theme = BirthdayTheme.PELICAN,
            imageBitmap = null
        )
    )
)

@Preview
@Composable
internal fun BirthdayScreenPelicanWithPhotoPreview(imageBitmap: ImageBitmap) = BirthdayScreen(
    viewModel = previewViewModel(
        viewState = BirthdayViewState(
            name = "Cristiano Ronaldo",
            anniversary = Anniversary(
                numbers = listOf(BirthdayNumbers.NUMBER_2),
                description = BirthdayDescription.YEARS

            ),
            theme = BirthdayTheme.PELICAN,
            imageBitmap = imageBitmap
        )
    )
)

internal fun previewViewModel(viewState: BirthdayViewState?): BirthdayViewModel = object : BirthdayViewModel() {
    override val viewState = MutableStateFlow(viewState)
    override fun updateSelectedImage(selectedImage: ImageBitmap) = Unit
}
