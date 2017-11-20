package rest.api.app


import grails.rest.*

import java.time.OffsetDateTime

@Resource(readOnly = false, formats = ['json', 'xml'])
class SupremeParent implements CreatedAndUpdatedAware{

    String label
    String description

    static constraints = {
        description nullable: true
    }

    static hasMany = [
            alphaParents:AlphaParent,
            betaParents: BetaParent
    ]
}