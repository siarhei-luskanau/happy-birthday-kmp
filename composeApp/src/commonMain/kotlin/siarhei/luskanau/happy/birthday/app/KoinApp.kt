package siarhei.luskanau.happy.birthday.app

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinMultiplatformApplication
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.module
import siarhei.luskanau.happy.birthday.app.di.allModules

@Preview
@Composable
fun KoinApp() = KoinMultiplatformApplication(
    config = KoinConfiguration {
        modules(
            *allModules(
                appModule = module {}
            ).toTypedArray()
        )
    }
) {
    NavigationApp()
}
