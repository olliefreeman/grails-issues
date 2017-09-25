package rest.api.app


import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class BetaChild {
    String label
    String description

    static constraints = {
        description nullable: true
    }

    static belongsTo = [
            parent:Parent
    ]
}