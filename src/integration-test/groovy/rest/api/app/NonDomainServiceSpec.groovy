package rest.api.app

import grails.core.ArtefactInfo
import grails.core.GrailsApplication
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@Integration
@Rollback
class NonDomainServiceSpec extends Specification {

    NonDomainService nonDomainService
    GrailsApplication grailsApplication

    void 'test artefact creation'(){
        given:
        ArtefactInfo artefactInfo = grailsApplication.getArtefactInfo('Service')
        ApplicationContext applicationContext = grailsApplication.parentContext

        expect:
        applicationContext.getBean('alphaChildService')
        applicationContext.getBean('alphaParentService')
        applicationContext.getBean('betaChildService')
        applicationContext.getBean('betaParentService')
        applicationContext.getBean('supremeParentService')
        artefactInfo.grailsClassesByName.size() == 7
    }

    void 'test service binding'(){

        expect:
        nonDomainService.supremeParentService
        nonDomainService.anotherService

        and:
        nonDomainService.supremeParentService.anotherService
    }
}
