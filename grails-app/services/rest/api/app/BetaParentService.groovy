package rest.api.app

import grails.gorm.services.Service

@Service(BetaParent)
abstract class BetaParentService implements SupremeAware {

    AlphaChildService alphaChildService
    AlphaParentService alphaParentService
    BetaChildService betaChildService
    SupremeParentService supremeParentService
    AnotherService anotherService

    abstract BetaParent get(Serializable id)

    abstract  List<BetaParent> list(Map args)

    abstract  Long count()

    abstract  void delete(Serializable id)

    abstract  BetaParent save(BetaParent betaParent)

}