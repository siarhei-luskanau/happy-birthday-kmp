package siarhei.luskanau.happy.birthday.core.network.ktor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayTheme
import siarhei.luskanau.happy.birthday.core.network.AnniversaryData
import siarhei.luskanau.happy.birthday.core.network.ServerApiService
import siarhei.luskanau.happy.birthday.core.network.ktor.model.ThemeDto

@Single
internal class KtorServerApiService(private val client: ServerApiClient) : ServerApiService {

    override fun openWebSocket(): Flow<AnniversaryData?> = client.openWebSocket()
        .map { anniversaryDto ->
            anniversaryDto?.let { dto ->
                if (dto.name != null && dto.dob != null && dto.theme != null) {
                    AnniversaryData(
                        name = dto.name,
                        dob = dto.dob,
                        theme = when (dto.theme) {
                            ThemeDto.ELEPHANT -> BirthdayTheme.ELEPHANT
                            ThemeDto.FOX -> BirthdayTheme.FOX
                            ThemeDto.PELICAN -> BirthdayTheme.PELICAN
                        }
                    )
                } else {
                    null
                }
            }
        }
}
