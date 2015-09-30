package rollback.issue

import grails.rest.Resource

import java.time.LocalDate

@Resource
class Rollover {

    String name
    String test
    LocalDate testDate

    static constraints = {
        name blank: false
        test nullable: true, blank: true
        testDate max: LocalDate.now()
    }
}
