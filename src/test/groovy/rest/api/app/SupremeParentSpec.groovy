package rest.api.app

import grails.testing.gorm.DomainUnitTest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import spock.lang.Specification

class SupremeParentSpec extends Specification implements DomainUnitTest<SupremeParent> {

    def setup() {
        mockDomains(BetaParent, BetaChild, AlphaParent, AlphaChild)
    }

    void "1: test invalid domain"() {
        given:
        domain.label = 'Test supreme parent'
        BetaParent betaParent = new BetaParent(label: 'test')
        AlphaChild alphaChild = new AlphaChild(label: 'achild')
        alphaChild.addToBetaChildren(description: 'desc')
        betaParent.addToAlphaChildren(alphaChild)
        domain.addToBetaParents(betaParent)

        when: 'creating a parent with invalid and valid children'
        AlphaParent parent = new AlphaParent(label: 'parent')
        domain.addToAlphaParents(parent)

        then:
        !check(domain)
        domain.errors.allErrors.size() == 1
    }

    void "2: test invalid nested domain"() {
        given:
        domain.label = 'Test supreme parent'
        BetaParent betaParent = new BetaParent(label: 'test')
        AlphaChild alphaChild = new AlphaChild(label: 'achild')
        alphaChild.addToBetaChildren(new BetaChild())
        betaParent.addToAlphaChildren(alphaChild)
        domain.addToBetaParents(betaParent)

        when: 'creating a parent with invalid and valid children'
        AlphaParent parent = new AlphaParent(label: 'parent')
        domain.addToAlphaParents(parent)
        AlphaParent parent1 = new AlphaParent(label: 'parent1')
        parent1.addToChildren(parent)
        domain.addToAlphaParents(parent1)

        then:
        !check(domain)
        domain.errors.allErrors.size() == 1
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
