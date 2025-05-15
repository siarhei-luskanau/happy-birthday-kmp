package siarhei.luskanau.happy.birthday.domain.birthday

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.datetime.Instant
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayNumbers

class BirthdayDomainServiceImplTest {

    private val birthdayDomainService = BirthdayDomainServiceImpl()

    @Test
    fun testCalculateAgeWithValidBirthdayAndToday() {
        val birthdayMillis = Instant.parse("2000-01-01T00:00:00Z").toEpochMilliseconds()
        val todayMillis = Instant.parse("2022-01-01T00:00:00Z").toEpochMilliseconds()

        val (age, birthdayType) = birthdayDomainService.calculateAge(birthdayMillis, todayMillis)

        assertEquals(22, age) // Expected age is 22 years
        assertEquals(BirthdayType.YEAR, birthdayType) // It should be YEAR
    }

    @Test
    fun testCalculateAgeWithValidBirthdayAndTodayLessThan1Year() {
        val birthdayMillis = Instant.parse("2020-06-15T00:00:00Z").toEpochMilliseconds()
        val todayMillis = Instant.parse("2020-12-15T00:00:00Z").toEpochMilliseconds()

        val (age, birthdayType) = birthdayDomainService.calculateAge(birthdayMillis, todayMillis)

        assertEquals(6, age) // Expected age in months is 6 months
        assertEquals(BirthdayType.MONTH, birthdayType) // It should be MONTH
    }

    @Test
    fun testCalculateAgeWithInvalidBirthdayWithFuture() {
        val birthdayMillis = Instant.parse("2023-01-01T00:00:00Z").toEpochMilliseconds()
        val todayMillis = Instant.parse("2022-01-01T00:00:00Z").toEpochMilliseconds()

        assertFailsWith(IllegalArgumentException::class) {
            birthdayDomainService.calculateAge(birthdayMillis, todayMillis)
        }
    }

    @Test
    fun testCalculateAgeWithInvalidTimestampValues() {
        assertFailsWith(IllegalArgumentException::class) {
            birthdayDomainService.calculateAge(-1, 0)
        }
    }

    @Test
    fun testToBirthdayNumbersWithValidMonthInput() {
        val month = 5
        val numbers = birthdayDomainService.toBirthdayNumbers(month, BirthdayType.MONTH)

        assertEquals(listOf(BirthdayNumbers.NUMBER_5), numbers)
    }

    @Test
    fun testToBirthdayNumbersWithInvalidMonthInput() {
        assertFailsWith(IllegalArgumentException::class) {
            birthdayDomainService.toBirthdayNumbers(13, BirthdayType.MONTH)
        }
    }

    @Test
    fun testToBirthdayNumbersWithValidYearInput() {
        val year = 22
        val numbers = birthdayDomainService.toBirthdayNumbers(year, BirthdayType.YEAR)

        assertEquals(
            listOf(BirthdayNumbers.NUMBER_2, BirthdayNumbers.NUMBER_2),
            numbers
        )
    }

    @Test
    fun testToBirthdayNumbersWithNegativeYearInput() {
        assertFailsWith(IllegalArgumentException::class) {
            birthdayDomainService.toBirthdayNumbers(-1, BirthdayType.YEAR)
        }
    }

    @Test
    fun testToBirthdayNumbersWithTooLargeYearInput() {
        assertFailsWith(IllegalArgumentException::class) {
            birthdayDomainService.toBirthdayNumbers(999, BirthdayType.YEAR)
        }
    }

    @Test
    fun testToBirthdayNumbersWithInvalidDigit() {
        assertFailsWith(IllegalArgumentException::class) {
            birthdayDomainService.toBirthdayNumbers(13, BirthdayType.MONTH)
        }
    }
}
