import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("composeMultiplatformKspConvention")
    id("testOptionsConvention")
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.buildConfig)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.websockets)
            implementation(project.dependencies.platform(libs.ktor.bom))
            implementation(projects.core.coreCommon)
        }
        jvmMain.dependencies {
            implementation(libs.ktor.client.cio)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.cio)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "siarhei.luskanau.happy.birthday.core.network"
    testOptions.configureTestOptions()
}

buildConfig {
    packageName("iarhei.luskanau.happy.birthday.core.network")
    useKotlinOutput {
        topLevelConstants = true
        internalVisibility = true
    }
    val serverHost = gradleLocalProperties(rootDir, providers).getProperty("SERVER_HOST")
        ?: System.getenv("SERVER_HOST")
        ?: "127.0.0.1"
    buildConfigField("String", "SERVER_HOST", "\"${serverHost}\"")
    val serverPort = gradleLocalProperties(rootDir, providers).getProperty("SERVER_PORT")
        ?: System.getenv("SERVER_PORT")
        ?: "8080"
    buildConfigField("Integer", "SERVER_PORT", serverPort.toInt())
}
