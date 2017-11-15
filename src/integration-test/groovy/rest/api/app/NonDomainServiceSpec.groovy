package rest.api.app

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification

@Integration
@Rollback
class NonDomainServiceSpec extends Specification {

    NonDomainService nonDomainService

    void 'test service binding'(){

        expect:
        nonDomainService.supremeParentService
        nonDomainService.anotherService

        and:
        nonDomainService.supremeParentService.anotherService
    }
}
