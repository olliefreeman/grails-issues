package rest.api.app

import grails.gorm.services.Service

@Service(AlphaChild)
abstract class AlphaChildService implements SupremeAware{

    AlphaParentService alphaParentService
    SupremeParentService supremeParentService

    abstract AlphaChild get(Serializable id)

    abstract List<AlphaChild> list(Map args)

    abstract  Long count()

    abstract  void delete(Serializable id)

    abstract  AlphaChild save(AlphaChild alphaChild)

}