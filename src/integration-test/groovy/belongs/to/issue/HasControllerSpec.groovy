package belongs.to.issue

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
import java.time.LocalDateTime

@Integration
@Rollback
class HasControllerSpec extends Specification {

    private static final Logger logger = LoggerFactory.getLogger(HasControllerSpec)

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


    def cleanup() {
        if (response?.text) {
            try {
                dump(response.xml?.errors?.size() ? 'error' : 'warn', true)
            } catch (Exception ignored) {
                dump('error')
            }
        }
    }

    def dump(String level, boolean format = false) {
        logger."$level" format ? XmlUtil.serialize(response.xml) : response.text
        if (level == 'error') System.err.println format ? XmlUtil.serialize(response.xml) : response.text
    }


    void "test get"() {

        when: 'calling rest api'
        response = rest.get("$baseUrl/test/belongs")

        then: 'we should get some xml'
        response.statusCode == HttpStatus.OK
        response.xml
    }

    void "test post"() {

        when: 'posting a new has to the rest api'
        response = rest.post("$baseUrl/test/belongs") {
            xml {
                has {
                    belongs {
                        belong {
                            test 'test 1'
                            date LocalDate.of(2015, 9, 28)
                        }
                        belong {
                            test 'test 2'
                            date LocalDate.of(2015, 9, 29)
                        }
                    }
                    datetime LocalDateTime.now()
                    sample 'a new has sample'
                }
            }
        }


        // Actually we get a 500 due to the inability to save as the belongs weren't validated first
        // And even if they are validated they fail validation as they have no owner.

        then: 'we should get a created response'
        response.statusCode == HttpStatus.CREATED

        and: 'the response should have expected xml'
        response.xml.sample == 'a new has sample'

    }
}
