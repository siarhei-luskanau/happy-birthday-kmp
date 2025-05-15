package siarhei.luskanau.happy.birthday.core.network

import kotlinx.coroutines.flow.Flow
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayTheme

interface ServerApiService {
    fun openWebSocket(): Flow<AnniversaryData?>
}

data class AnniversaryData(val name: String, val dob: Long, val theme: BirthdayTheme)
