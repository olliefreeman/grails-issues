package rest.api.app

import grails.rest.Resource

@Resource(readOnly = false, formats = ['json', 'xml'])
class AlphaChild {
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