package rest.api.app

import grails.gorm.services.Service

@Service(SupremeParent)
abstract class SupremeParentService {

    AlphaParentService alphaParentService
    AlphaChildService alphaChildService
    BetaParentService betaParentService
    AnotherService anotherService

    abstract SupremeParent get(Serializable id)

    abstract  List<SupremeParent> list(Map args)

    abstract  Long count()

    void delete(Serializable id){

    }

    abstract SupremeParent save(SupremeParent supremeParent)

    def doSomething(){

    }

}