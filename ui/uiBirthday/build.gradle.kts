plugins {
    id("composeMultiplatformKspConvention")
    id("testOptionsConvention")
    alias(libs.plugins.roborazzi)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.coreCommon)
            implementation(projects.core.coreNetwork)
            implementation(projects.domain.domainBirthday)
            implementation(projects.ui.uiCommon)
        }
        androidUnitTest.dependencies {
            implementation(projects.ui.uiScreenshotTest)
        }
    }
}

android {
    namespace = "siarhei.luskanau.happy.birthday.ui.birthday"
    testOptions.configureTestOptions()
}

// Directory for reference images
roborazzi.outputDir.set(rootProject.file("screenshots"))
