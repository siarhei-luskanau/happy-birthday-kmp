package siarhei.luskanau.happy.birthday.app

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.koin.compose.getKoin
import org.koin.core.parameter.parametersOf
import siarhei.luskanau.happy.birthday.app.theme.AppTheme
import siarhei.luskanau.happy.birthday.ui.birthday.BirthdayScreen

@Composable
fun NavigationApp() = AppTheme {
    val koin = getKoin()
    val navHostController: NavHostController = rememberNavController()
    val navigationCallbacks = NavigationCallbacks(navHostController = navHostController)

    NavHost(
        navController = navHostController,
        startDestination = AppRoutes.Birthday
    ) {
        composable<AppRoutes.Birthday> {
            BirthdayScreen(
                viewModel = viewModel { koin.get { parametersOf(navigationCallbacks) } }
            )
        }
    }
}

internal sealed interface AppRoutes {

    @Serializable
    data object Birthday : AppRoutes
}
