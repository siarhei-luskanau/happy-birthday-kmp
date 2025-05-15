plugins {
    id("composeMultiplatformKspConvention")
    id("testOptionsConvention")
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(projects.ui.uiBirthday)
            implementation(projects.ui.uiCommon)
        }
    }
}

android {
    namespace = "siarhei.luskanau.happy.birthday.navigation"
    testOptions.configureTestOptions()
}
