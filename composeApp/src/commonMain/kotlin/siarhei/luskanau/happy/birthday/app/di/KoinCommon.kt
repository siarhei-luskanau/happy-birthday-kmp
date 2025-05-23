package siarhei.luskanau.happy.birthday.app.di

import org.koin.core.module.Module
import org.koin.ksp.generated.module
import siarhei.luskanau.happy.birthday.core.network.CoreNetworkModule
import siarhei.luskanau.happy.birthday.domain.birthday.DomainBirthdayModule
import siarhei.luskanau.happy.birthday.ui.birthday.UiBirthdayModule

fun allModules(appModule: Module): List<Module> = listOf(
    appModule,
    appPlatformModule,
    CoreNetworkModule().module,
    DomainBirthdayModule().module,
    UiBirthdayModule().module
)

expect val appPlatformModule: Module
