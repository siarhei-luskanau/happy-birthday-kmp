package siarhei.luskanau.happy.birthday.ui.birthday

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import network.chaintech.cmpimagepickncrop.utils.SharedImage

abstract class BirthdayViewModel : ViewModel() {
    abstract val viewState: StateFlow<BirthdayViewState?>
    abstract fun updateSelectedImage(selectedImage: ImageBitmap)
    abstract fun updateSelectedImageFile(sharedImage: SharedImage)
}
