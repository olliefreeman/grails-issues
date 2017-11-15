package rest.api.app

import grails.rest.Resource

@Resource(readOnly = false, formats = ['json', 'xml'])
class AlphaParent {

    String label
    String description

    static constraints = {
        description nullable: true
    }

    static hasMany = [
            children: AlphaParent
    ]

    static belongsTo = [
            supremeParent: SupremeParent,
            parent       : AlphaParent
    ]

    static mapping = {
        children cascade: 'all-delete-orphan'
    }
}