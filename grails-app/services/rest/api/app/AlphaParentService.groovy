package rest.api.app

import grails.gorm.services.Service

@Service(AlphaParent)
abstract class AlphaParentService implements SupremeAware{

    AlphaChildService alphaChildService
    SupremeParentService supremeParentService
    AnotherService anotherService

    abstract AlphaParent get(Serializable id)

    abstract  List<AlphaParent> list(Map args)

    abstract  Long count()

    abstract void delete(Serializable id)

    abstract AlphaParent save(AlphaParent alphaParent)

}