package siarhei.luskanau.happy.birthday.domain.birthday

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.periodUntil
import org.koin.core.annotation.Single
import siarhei.luskanau.happy.birthday.core.common.model.Anniversary
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayDescription
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayNumbers

@Single
internal class BirthdayDomainServiceImpl : BirthdayDomainService {

    override fun calculateAnniversary(birthday: Long, today: Long): Anniversary =
        calculateAge(birthday, today).let { (amount, type) ->
            Anniversary(
                numbers = toBirthdayNumbers(amount = amount, type = type),
                description = when (type) {
                    BirthdayType.MONTH -> if (amount > 1) BirthdayDescription.MONTHS else BirthdayDescription.MONTH
                    BirthdayType.YEAR -> if (amount > 1) BirthdayDescription.YEARS else BirthdayDescription.YEAR
                }
            )
        }

    internal fun calculateAge(birthday: Long, today: Long): Pair<Int, BirthdayType> =
        if (birthday > 0 && today > birthday) {
            val birthdayInstant = Instant.fromEpochMilliseconds(epochMilliseconds = birthday)
            val todayInstant = Instant.fromEpochMilliseconds(epochMilliseconds = today)
            val dateTimePeriod = birthdayInstant.periodUntil(other = todayInstant, timeZone = TimeZone.UTC)
            if (dateTimePeriod.years >= 1) {
                dateTimePeriod.years to BirthdayType.YEAR
            } else {
                dateTimePeriod.months to BirthdayType.MONTH
            }
        } else {
            throw IllegalArgumentException("Incorrect birthday ($birthday) or today ($today)")
        }

    internal fun toBirthdayNumbers(amount: Int, type: BirthdayType): List<BirthdayNumbers> = when (type) {
        BirthdayType.MONTH -> listOf(
            when (amount) {
                0 -> BirthdayNumbers.NUMBER_0
                1 -> BirthdayNumbers.NUMBER_1
                2 -> BirthdayNumbers.NUMBER_2
                3 -> BirthdayNumbers.NUMBER_3
                4 -> BirthdayNumbers.NUMBER_4
                5 -> BirthdayNumbers.NUMBER_5
                6 -> BirthdayNumbers.NUMBER_6
                7 -> BirthdayNumbers.NUMBER_7
                8 -> BirthdayNumbers.NUMBER_8
                9 -> BirthdayNumbers.NUMBER_9
                10 -> BirthdayNumbers.NUMBER_10
                11 -> BirthdayNumbers.NUMBER_11
                12 -> BirthdayNumbers.NUMBER_12
                else -> throw IllegalArgumentException("Incorrect amount of month: $amount")
            }
        )
        BirthdayType.YEAR -> if (amount <= 0) {
            throw IllegalArgumentException("Incorrect amount of year: negative value $amount")
        } else if (amount > 200) {
            throw IllegalArgumentException("Incorrect amount of year: too big value $amount")
        } else {
            amount.toString().map {
                when (it) {
                    '0' -> BirthdayNumbers.NUMBER_0
                    '1' -> BirthdayNumbers.NUMBER_1
                    '2' -> BirthdayNumbers.NUMBER_2
                    '3' -> BirthdayNumbers.NUMBER_3
                    '4' -> BirthdayNumbers.NUMBER_4
                    '5' -> BirthdayNumbers.NUMBER_5
                    '6' -> BirthdayNumbers.NUMBER_6
                    '7' -> BirthdayNumbers.NUMBER_7
                    '8' -> BirthdayNumbers.NUMBER_8
                    '9' -> BirthdayNumbers.NUMBER_9
                    else -> throw IllegalArgumentException("Incorrect digit: $it")
                }
            }
        }
    }
}

internal enum class BirthdayType { MONTH, YEAR }
