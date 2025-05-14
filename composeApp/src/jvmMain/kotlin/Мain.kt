import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension
import siarhei.luskanau.happy.birthday.app.App

fun main() = application {
    Window(
        title = "Happy Birthday",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication
    ) {
        window.minimumSize = Dimension(350, 600)
        App()
    }
}
