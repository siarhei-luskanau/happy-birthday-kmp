import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import siarhei.luskanau.happy.birthday.app.App

fun mainViewController(): UIViewController = ComposeUIViewController { App() }
