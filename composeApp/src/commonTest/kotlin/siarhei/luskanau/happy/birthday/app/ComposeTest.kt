package siarhei.luskanau.happy.birthday.app

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.runComposeUiTest
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class ComposeTest {

    @Test
    fun simpleCheck() = runComposeUiTest {
        setContent {
            // workaround "CompositionLocal LocalLifecycleOwner not present"
            // https://youtrack.jetbrains.com/issue/CMP-7419/CompositionLocal-LocalLifecycleOwner-not-present-on-iosSimulatorArm64Test-task
            CompositionLocalProvider(
                LocalLifecycleOwner provides LocalLifecycleOwnerFake()
            ) {
                KoinApp()
            }
        }

        onNodeWithTag(testTag = "t_nanit")
            .assertExists()
            .assertContentDescriptionEquals("Nanit Logo")
    }
}

private class LocalLifecycleOwnerFake : LifecycleOwner {
    override val lifecycle: Lifecycle = LifecycleRegistry(this).apply {
        currentState = Lifecycle.State.RESUMED
    }
}
