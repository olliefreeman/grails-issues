class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        group '/test', {
            '/rollbacks'(resources: "rollback")
            '/belongs'(resources: 'has')
        }
    }
}
