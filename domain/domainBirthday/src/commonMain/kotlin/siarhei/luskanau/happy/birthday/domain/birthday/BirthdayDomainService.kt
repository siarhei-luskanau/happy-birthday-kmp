package siarhei.luskanau.happy.birthday.domain.birthday

import siarhei.luskanau.happy.birthday.core.common.model.Anniversary

interface BirthdayDomainService {
    fun calculateAnniversary(birthday: Long, today: Long): Anniversary
}
