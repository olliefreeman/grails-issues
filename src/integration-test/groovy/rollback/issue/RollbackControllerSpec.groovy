package rollback.issue

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import groovy.xml.XmlUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import spock.lang.Shared
import spock.lang.Specification

import javax.annotation.Resource
import java.time.LocalDate

@Integration
@Rollback
class RollbackControllerSpec extends Specification {

    private static final Logger logger = LoggerFactory.getLogger(RollbackControllerSpec)

    @Resource
    MessageSource messageSource

    @Shared
    RestBuilder rest
    @Shared
    RestResponse response
    @Shared
    String baseUrl = 'http://localhost:8080'

    def setup() {
        rest = new RestBuilder()
    }

    def setupData() {

        new rollback.issue.Rollback(
                name: 'a name',
                test: 'a value',
                testDate: LocalDate.of(2015, 9, 28)
        ).save()
    }

    def cleanup() {
        if (response.text) {
            try {
                logger.info XmlUtil.serialize(response.text)
            } catch (Exception ignored) {
                logger.error response.text
            }
        }
    }

    void "test empty list"() {

        given:
        !rollback.issue.Rollback.list()

        when: "call to index with no data"
        response = rest.get("$baseUrl/test/rollbacks")

        then: 'the list is empty'
        response.statusCode == HttpStatus.OK
        response.xml.children().size() == 0
    }

    void "test list with data"() {

        given: 'we save the domain first'
        setupData()

        when: 'call is made to index'
        response = rest.get("$baseUrl/test/rollbacks")

        then: 'there should be the domain returned in the list'
        response.statusCode == HttpStatus.OK
        response.xml.children().size() == 1

        and: 'there should be expected metadata in the xml'
        response.xml.rollback.metadata.size() == 1
        response.xml.rollback.metadata.'schema-name' == 'Death'
    }
}
