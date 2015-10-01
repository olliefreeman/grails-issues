import belongs.to.issue.Belong
import belongs.to.issue.Has
import databinding.JavaLocalDateConverter
import databinding.JavaLocalDateTimeConverter
import grails.rest.render.xml.XmlRenderer

// Place your Spring DSL code here
beans = {

    hasRenderer(XmlRenderer, Has){
        excludes = ['id']
    }
    belongRenderer(XmlRenderer, Belong){
        excludes = ['id','owner']
    }

    localDateConverter(JavaLocalDateConverter)
    offsetDateTimeConverter(JavaLocalDateTimeConverter)
}
