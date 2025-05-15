plugins {
    id("composeMultiplatformConvention")
    id("testOptionsConvention")
}

android {
    namespace = "siarhei.luskanau.happy.birthday.core.common"
    testOptions.configureTestOptions()
}
