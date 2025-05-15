package siarhei.luskanau.happy.birthday.core.network.ktor.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnniversaryDto(
    @SerialName("name") val name: String?,
    @SerialName("dob")val dob: Long?,
    @SerialName("theme")val theme: ThemeDto?
)

@Serializable
enum class ThemeDto {
    @SerialName("elephant")
    ELEPHANT,

    @SerialName("fox")
    FOX,

    @SerialName("pelican")
    PELICAN
}
