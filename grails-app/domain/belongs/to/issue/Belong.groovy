package belongs.to.issue

import java.time.LocalDate

class Belong {

    String test
    LocalDate date

    static belongsTo = [owner: Has]

    static constraints = {
        test blank: false
        date max: LocalDate.now()
    }
}
