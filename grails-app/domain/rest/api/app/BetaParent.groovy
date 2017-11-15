package rest.api.app


import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class BetaParent {
    String label
    String description

    static constraints = {
        description nullable: true
    }

    static hasMany = [
            alphaChildren: AlphaChild
    ]

    static belongsTo = [
            supremeParent:SupremeParent
    ]
}