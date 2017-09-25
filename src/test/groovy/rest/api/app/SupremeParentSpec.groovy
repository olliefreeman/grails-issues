package rest.api.app

import grails.testing.gorm.DomainUnitTest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import spock.lang.Specification

class SupremeParentSpec extends Specification implements DomainUnitTest<SupremeParent> {

    def setup() {
        mockDomains(AlphaChild, BetaChild, Parent)
    }

    void "1: test invalid domain"() {
        given:
        domain.label = 'Test supreme parent'

        when: 'creating a parent with invalid and valid children'
        Parent parent = new Parent(label: 'parent')
        parent.addToBetaChildren(new BetaChild())
        parent.addToAlphaChildren(new AlphaChild())
        domain.addToParents(parent)

        then:
        !check(domain)
        domain.errors.allErrors.size() == 2
    }

    void "2: test invalid nested domain"() {
        given:
        domain.label = 'Test supreme parent'

        when: 'creating a parent with invalid and valid children'
        Parent parent = new Parent(label: 'parent')
        Parent parent1 = new Parent(label: 'parent1')
        parent.addToBetaChildren(new BetaChild())
        parent1.addToChildren(parent)
        domain.addToParents(parent1)

        then:
        !check(domain)
        domain.errors.allErrors.size() == 2
    }

    void "3: test invalid looped domain"() {
        given:
        domain.label = 'Test supreme parent'

        when: 'creating a parent with invalid and valid children'
        Parent parent = new Parent(label: 'parent')
        parent.addToChildren(new Parent())
        domain.addToParents(parent)

        then:
        !check(domain)
        domain.errors.allErrors.size() == 2
    }

    Logger getLogger() {
        LoggerFactory.getLogger(getClass())
    }

    boolean check(def domainObj) {
        if (!domainObj) return true

        boolean valid = domainObj.validate()

        if (!valid) {
            outputDomainErrors(domainObj, messageSource)
            return false
        }
        true
    }

    def outputDomainErrors(def domainObj, MessageSource messageSource) {
        logger.error 'Errors validating domain: {}', domainObj.class.simpleName
        System.err.println 'Errors validating domain: ' + domainObj.class.simpleName
        domainObj.errors.allErrors.each {error ->
            if (messageSource) {
                logger.error messageSource.getMessage(error, Locale.default)
                System.err.println messageSource.getMessage(error, Locale.default)
            }
            else {
                logger.error error.defaultMessage
                logger.error "${Arrays.asList(error.arguments)}"
            }
        }
    }
}
