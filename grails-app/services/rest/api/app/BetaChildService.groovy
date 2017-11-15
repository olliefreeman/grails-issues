package rest.api.app

import grails.gorm.services.Service

@Service(BetaChild)
abstract class BetaChildService implements SupremeAware{

    BetaParentService betaParentService
    SupremeParentService supremeParentService

    abstract   BetaChild get(Serializable id)

    abstract  List<BetaChild> list(Map args)

    abstract  Long count()

    abstract void delete(Serializable id)

    abstract BetaChild save(BetaChild betaChild)

}