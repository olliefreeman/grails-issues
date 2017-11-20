package rest.api.app

import grails.rest.Resource

import java.time.OffsetDateTime

@Resource(readOnly = false, formats = ['json', 'xml'])
class AlphaParent implements CreatedAndUpdatedAware {


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