package belongs.to.issue

import grails.rest.Resource
import validator.HasManySizeValidator

import java.time.LocalDate
import java.time.LocalDateTime

@Resource
class Has {

    String sample
    LocalDateTime datetime
    String name

    static hasMany = [belongs: Belong]

    static constraints = {
        sample blank: false
        datetime nullable: true
        name blank: true, nullable: true
    }
}
