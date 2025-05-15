package siarhei.luskanau.happy.birthday.app.di

import org.koin.core.module.Module
import org.koin.ksp.generated.module
import siarhei.luskanau.happy.birthday.core.common.CoreCommonModule
import siarhei.luskanau.happy.birthday.core.network.CoreNetworkModule

fun allModules(appModule: Module): List<Module> = listOf(
    appModule,
    appPlatformModule,
    CoreCommonModule().module,
    CoreNetworkModule().module
)

expect val appPlatformModule: Module
