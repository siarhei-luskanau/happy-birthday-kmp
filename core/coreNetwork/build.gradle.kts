plugins {
    id("composeMultiplatformKspConvention")
    id("testOptionsConvention")
}

android {
    namespace = "siarhei.luskanau.happy.birthday.core.network"
    testOptions.configureTestOptions()
}
