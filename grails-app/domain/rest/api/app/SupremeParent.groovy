package rest.api.app


import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class SupremeParent {

    String label
    String description

    static constraints = {
        description nullable: true
    }

    static hasMany = [
            parents:Parent
    ]
}