plugins {
    id("composeMultiplatformKspConvention")
    id("testOptionsConvention")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.coreCommon)
        }
    }
}

android {
    namespace = "siarhei.luskanau.happy.birthday.domain.birthday"
    testOptions.configureTestOptions()
}
