package siarhei.luskanau.happy.birthday.app

import androidx.navigation.NavHostController

class NavigationCallbacks(private val navHostController: NavHostController) {
    fun goBack() {
        navHostController.popBackStack()
    }
}
