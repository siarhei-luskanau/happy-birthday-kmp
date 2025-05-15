package siarhei.luskanau.happy.birthday.domain.birthday

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue
import kotlinx.datetime.Instant
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayDescription
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayNumbers

class BirthdayDomainServiceTest {

    private val birthdayDomainService: BirthdayDomainService = BirthdayDomainServiceImpl()

    @Test
    fun shouldReturnCorrectAnniversaryFor1YearAnniversary() {
        val birthday = Instant.parse("2024-05-15T00:00:00Z").toEpochMilliseconds()
        val today = Instant.parse("2025-05-15T00:00:00Z").toEpochMilliseconds()

        val result = birthdayDomainService.calculateAnniversary(birthday, today)

        assertEquals(BirthdayDescription.YEAR, result.description)
        assertTrue(result.numbers.contains(BirthdayNumbers.NUMBER_1)) // Expecting "1"
    }

    @Test
    fun shouldReturnCorrectAnniversaryFor2YearsAnniversary() {
        val birthday = Instant.parse("2023-05-15T00:00:00Z").toEpochMilliseconds()
        val today = Instant.parse("2025-05-15T00:00:00Z").toEpochMilliseconds()

        val result = birthdayDomainService.calculateAnniversary(birthday, today)

        assertEquals(BirthdayDescription.YEARS, result.description)
        assertTrue(result.numbers.contains(BirthdayNumbers.NUMBER_2)) // Expecting "2"
    }

    @Test
    fun shouldReturnCorrectAnniversaryFor5MonthsAnniversary() {
        val birthday = Instant.parse("2024-01-15T00:00:00Z").toEpochMilliseconds()
        val today = Instant.parse("2024-06-15T00:00:00Z").toEpochMilliseconds()

        val result = birthdayDomainService.calculateAnniversary(birthday, today)

        assertEquals(BirthdayDescription.MONTHS, result.description)
        assertTrue(result.numbers.contains(BirthdayNumbers.NUMBER_5)) // Expecting "5"
    }

    @Test
    fun shouldReturnCorrectAnniversaryFor1MonthAnniversary() {
        val birthday = Instant.parse("2024-04-15T00:00:00Z").toEpochMilliseconds()
        val today = Instant.parse("2024-05-15T00:00:00Z").toEpochMilliseconds()

        val result = birthdayDomainService.calculateAnniversary(birthday, today)

        assertEquals(BirthdayDescription.MONTH, result.description)
        assertTrue(result.numbers.contains(BirthdayNumbers.NUMBER_1)) // Expecting "1"
    }

    @Test
    fun shouldThrowExceptionForIncorrectBirthdayTodayIsBeforeBirthday() {
        val birthday = Instant.parse("2025-05-15T00:00:00Z").toEpochMilliseconds()
        val today = Instant.parse("2024-05-15T00:00:00Z").toEpochMilliseconds()

        val exception = assertFailsWith<IllegalArgumentException> {
            birthdayDomainService.calculateAnniversary(birthday, today)
        }

        assertEquals("Incorrect birthday ($birthday) or today ($today)", exception.message)
    }

    @Test
    fun shouldThrowExceptionForIncorrectBirthdayNegativeValue() {
        val birthday = -1234567890L
        val today = Instant.parse("2025-05-15T00:00:00Z").toEpochMilliseconds()

        val exception = assertFailsWith<IllegalArgumentException> {
            birthdayDomainService.calculateAnniversary(birthday, today)
        }

        assertEquals("Incorrect birthday ($birthday) or today ($today)", exception.message)
    }

    @Test
    fun shouldThrowExceptionForInvalidYearValueTooLarge() {
        val birthday = Instant.parse("2025-05-15T00:00:00Z").toEpochMilliseconds()
        val today = Instant.parse("2230-05-15T00:00:00Z").toEpochMilliseconds()

        val exception = assertFailsWith<IllegalArgumentException> {
            birthdayDomainService.calculateAnniversary(birthday, today)
        }

        assertEquals("Incorrect amount of year: too big value 205", exception.message)
    }
}
