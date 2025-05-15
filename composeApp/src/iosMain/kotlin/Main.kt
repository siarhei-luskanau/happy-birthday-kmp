import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import siarhei.luskanau.happy.birthday.app.KoinApp

fun mainViewController(): UIViewController = ComposeUIViewController { KoinApp() }
