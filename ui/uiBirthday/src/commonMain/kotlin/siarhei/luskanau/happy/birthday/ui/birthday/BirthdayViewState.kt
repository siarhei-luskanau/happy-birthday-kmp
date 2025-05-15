package siarhei.luskanau.happy.birthday.ui.birthday

import siarhei.luskanau.happy.birthday.core.common.model.Anniversary
import siarhei.luskanau.happy.birthday.core.common.model.BirthdayTheme

data class BirthdayViewState(val name: String, val anniversary: Anniversary, val theme: BirthdayTheme)
