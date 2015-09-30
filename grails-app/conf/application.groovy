import org.jadira.usertype.dateandtime.threeten.PersistentLocalDate
import org.jadira.usertype.dateandtime.threeten.PersistentOffsetDateTime

import java.time.LocalDate
import java.time.OffsetDateTime

grails.gorm.default.constraints = {
    phoneNumber(matches: /(((\+44)? ?(\(0\))? ?)|(0))( ?[0-9]{3,4}){3}/)
    postcode(matches: /(GIR ?0AA|[A-PR-UWYZ]([0-9]{1,2}|[A-HK-Y][0-9]([0-9ABEHMNPRV-Y])?|[0-9][A-HJKPS-UW]) ?[0-9][ABD-HJLNP-UW-Z]{2})/)
    emailAddress(matches: /[\w\-\.]+@([\w-]+\.)+[\w-]{2,4}/)
}

grails.gorm.default.mapping = {
    "user-type" type: PersistentLocalDate, class: LocalDate
    "user-type" type: PersistentOffsetDateTime, class: OffsetDateTime
}