plugins {
    id("composeMultiplatformKspConvention")
    id("testOptionsConvention")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.ui.uiCommon)
        }
    }
}

android {
    namespace = "siarhei.luskanau.happy.birthday.navigation"
    testOptions.configureTestOptions()
}
