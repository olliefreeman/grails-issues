package rest.api.app

import grails.rest.Resource

import java.time.OffsetDateTime

@Resource(readOnly = false, formats = ['json', 'xml'])
class AlphaChild implements CreatedAndUpdatedAware{
    String label
    String description

    static constraints = {
        description nullable: true
    }

    static hasMany = [
            betaChildren: BetaChild
    ]

    static belongsTo = [
            betaParent: BetaParent
    ]
}