package siarhei.luskanau.happy.birthday.core.network

import kotlinx.coroutines.flow.Flow

interface ServerApiService {
    fun openWebSocket(): Flow<AnniversaryData?>
}

data class AnniversaryData(val name: String?, val dob: Long?, val theme: ThemeData?)

enum class ThemeData { ELEPHANT, FOX, PELICAN }
