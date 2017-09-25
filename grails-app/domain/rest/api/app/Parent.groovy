package rest.api.app


import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class Parent {

    String label
    String description

    static constraints = {
        description nullable: true
    }

    static hasMany = [
            alphaChildren: AlphaChild,
            betaChildren: BetaChild,
            children: Parent
    ]

    static belongsTo = [
            supremeParent:SupremeParent,
            parent: Parent
    ]


}