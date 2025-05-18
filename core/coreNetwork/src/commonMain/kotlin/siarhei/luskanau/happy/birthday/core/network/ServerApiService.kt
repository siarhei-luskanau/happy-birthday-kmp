package siarhei.luskanau.happy.birthday.core.network

import siarhei.luskanau.happy.birthday.core.common.model.BirthdayTheme

interface ServerApiService {
    suspend fun listenAnniversaryData(callback: (AnniversaryData?) -> Unit)
}

data class AnniversaryData(val name: String, val dob: Long, val theme: BirthdayTheme)
