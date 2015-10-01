import belongs.to.issue.Has
import grails.converters.XML

import java.time.LocalDate
import java.time.LocalDateTime

class BootStrap {

    def init = {servletContext ->


        new Has(
                sample: 'a sample',
                datetime: LocalDateTime.now().minusDays(1),
                name: 'testing'
        ).addToBelongs(
                test: 'testing string',
                date: LocalDate.of(2015, 10, 1)
        ).save()

        XML.registerObjectMarshaller(LocalDate) {
            it?.toString()
        }

        XML.registerObjectMarshaller(LocalDateTime) {
            it?.toString()
        }

    }
    def destroy = {
    }
}
