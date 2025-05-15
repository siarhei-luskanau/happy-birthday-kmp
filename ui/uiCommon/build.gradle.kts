plugins {
    id("composeMultiplatformKspConvention")
    id("testOptionsConvention")
}

android {
    namespace = "siarhei.luskanau.happy.birthday.ui.common"
    testOptions.configureTestOptions()
}

compose.resources {
    publicResClass = true
    packageOfResClass = "siarhei.luskanau.happy.birthday.ui.common.resources"
    generateResClass = always
}
