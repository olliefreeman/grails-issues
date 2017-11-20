package rest.api.app

import grails.plugin.json.builder.JsonOutput
import grails.plugin.json.view.test.JsonViewTest
import grails.testing.gorm.DomainUnitTest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Specification

/**
 * @since 20/11/2017
 */
class JsonGeneratorConverterAccessSpec extends Specification implements JsonViewTest, DomainUnitTest<SupremeParent> {

    private static final Logger logger = LoggerFactory.getLogger(JsonGeneratorConverterAccessSpec.class)

    void 'test access to converters in json views'(){
        given:
        SupremeParent domain = new SupremeParent()
        domain.label = 'Test supreme parent'
        domain.validate()
        domain.save(flush:true)

        when:
        def result = render(template: '/supremeParent/supremeParent', model: [supremeParent: domain])
        logger.info('{}', JsonOutput.prettyPrint(result.jsonText))

        then:
        result.json.dateCreated instanceof String
        result.json.lastUpdated instanceof String
    }
}
