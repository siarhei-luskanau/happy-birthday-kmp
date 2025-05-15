package siarhei.luskanau.happy.birthday.ui.birthday

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
        composeRule.setContent { AppTheme { BirthdayScreenFoxPreview() } }
        composeRule.onRoot().captureScreenshotDump(name = "dump")
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
}
