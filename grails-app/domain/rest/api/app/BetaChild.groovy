package rest.api.app


import grails.rest.*

import java.time.OffsetDateTime

@Resource(readOnly = false, formats = ['json', 'xml'])
class BetaChild implements CreatedAndUpdatedAware{

    String label
    String description

    static constraints = {
        description nullable: true
    }

    static belongsTo = [
            alphaChild: BetaParent
    ]
}