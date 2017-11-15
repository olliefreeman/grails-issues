package rest.api.app

import grails.gorm.transactions.Transactional

@Transactional
class AnotherService implements SupremeAware {

    SupremeParentService supremeParentService

    def serviceMethod() {

    }
}
