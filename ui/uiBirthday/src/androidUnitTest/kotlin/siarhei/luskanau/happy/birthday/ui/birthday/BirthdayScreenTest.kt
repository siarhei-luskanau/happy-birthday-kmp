package siarhei.luskanau.happy.birthday.ui.birthday

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import kotlin.test.BeforeTest
import kotlin.test.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import siarhei.luskanau.happy.birthday.app.theme.AppTheme
import siarhei.luskanau.happy.birthday.ui.screenshot.test.BaseScreenshotTest

@GraphicsMode(GraphicsMode.Mode.NATIVE)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], qualifiers = RobolectricDeviceQualifiers.SmallPhone)
class BirthdayScreenTest : BaseScreenshotTest(group = "birthday") {

    @get:Rule
    val composeRule = createComposeRule()

    @BeforeTest
    fun setup() {
        setupAndroidContextProvider()
    }

    @Test
    fun dumpTest() {
        composeRule.setContent { AppTheme { BirthdayScreenPelicanWithPhotoPreview(imageBitmap()) } }
        composeRule.onRoot().captureScreenshotDump(name = "dump")
    }

    @Test
    fun lightEmptyTest() {
        composeRule.setContent { AppTheme { BirthdayScreenEmptyPreview() } }
        composeRule.onRoot().captureScreenshot(name = "empty_light")
    }

    @Test
    @Config(qualifiers = "+night")
    fun nightEmptyTest() {
        composeRule.setContent { AppTheme { BirthdayScreenEmptyPreview() } }
        composeRule.onRoot().captureScreenshot(name = "empty_night")
    }

    @Test
    fun lightElephantTest() {
        composeRule.setContent { AppTheme { BirthdayScreenElephantPreview() } }
        composeRule.onRoot().captureScreenshot(name = "elephant_light")
    }

    @Test
    @Config(qualifiers = "+night")
    fun nightElephantTest() {
        composeRule.setContent { AppTheme { BirthdayScreenElephantPreview() } }
        composeRule.onRoot().captureScreenshot(name = "elephant_night")
    }

    @Test
    fun lightFoxTest() {
        composeRule.setContent { AppTheme { BirthdayScreenFoxPreview() } }
        composeRule.onRoot().captureScreenshot(name = "fox_light")
    }

    @Test
    @Config(qualifiers = "+night")
    fun nightFoxTest() {
        composeRule.setContent { AppTheme { BirthdayScreenFoxPreview() } }
        composeRule.onRoot().captureScreenshot(name = "fox_night")
    }

    @Test
    fun lightPelicanTest() {
        composeRule.setContent { AppTheme { BirthdayScreenPelicanPreview() } }
        composeRule.onRoot().captureScreenshot(name = "pelican_light")
    }

    @Test
    @Config(qualifiers = "+night")
    fun nightPelicanTest() {
        composeRule.setContent { AppTheme { BirthdayScreenPelicanPreview() } }
        composeRule.onRoot().captureScreenshot(name = "pelican_night")
    }

    @Test
    fun lightPhotoTest() {
        composeRule.setContent { AppTheme { BirthdayScreenPelicanWithPhotoPreview(imageBitmap()) } }
        composeRule.onRoot().captureScreenshot(name = "photo_light")
    }

    @Test
    @Config(qualifiers = "+night")
    fun nightPhotoTest() {
        composeRule.setContent { AppTheme { BirthdayScreenPelicanWithPhotoPreview(imageBitmap()) } }
        composeRule.onRoot().captureScreenshot(name = "photo_night")
    }

    private fun imageBitmap(): ImageBitmap =
        requireNotNull(javaClass.getResourceAsStream("/lily_face.png")).readAllBytes().let { bytes ->
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size).asImageBitmap()
        }
}
