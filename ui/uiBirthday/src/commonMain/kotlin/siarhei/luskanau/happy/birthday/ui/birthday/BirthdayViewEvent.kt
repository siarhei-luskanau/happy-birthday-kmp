package siarhei.luskanau.happy.birthday.ui.birthday

import androidx.compose.ui.graphics.ImageBitmap
import network.chaintech.cmpimagepickncrop.utils.SharedImage

sealed interface BirthdayViewEvent {
    data class SelectedImage(val image: ImageBitmap) : BirthdayViewEvent
    data class SelectedImageFile(val sharedImage: SharedImage) : BirthdayViewEvent
}
