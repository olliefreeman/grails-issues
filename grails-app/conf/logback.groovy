import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.filter.Filter
import ch.qos.logback.core.spi.FilterReply
import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

// Message
String ansiPattern = '%clr(%d{ISO8601}){faint} ' + // Date
                     '%clr([%10.10thread]){faint} ' + // Thread
                     '%clr(%-5level) ' + // Log level
                     '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                     '%m%n%wex'
// Message

String nonAnsiPattern = '%d{ISO8601} [%10.10thread] %-5level %-40.40logger{39} : %msg%n'

def baseDir = Environment.current == Environment.PRODUCTION ? BuildSettings.BASE_DIR.canonicalFile : BuildSettings.TARGET_DIR.canonicalFile
def clazz = Environment.current == Environment.PRODUCTION ? RollingFileAppender : FileAppender

File logDir = new File(baseDir, 'logs').canonicalFile
String logFilename = baseDir.name


// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')
        pattern = Environment.current == Environment.TEST ? nonAnsiPattern : ansiPattern
    }

    filter(ThresholdFilter) {
        level = INFO
    }

    //  filter(HibernateMappingFilter)
    filter HibernateDeprecationFilter
}

appender("FILE", clazz) {
    file = "${logDir}/${logFilename}.log"
    append = false

    encoder(PatternLayoutEncoder) {
        pattern = nonAnsiPattern
    }
    //   filter(HibernateMappingFilter)
    filter HibernateDeprecationFilter

    filter(ThresholdFilter) {
        level = TRACE
    }
}


root(INFO, ['STDOUT', 'FILE'])
logger('rest.api.app', DEBUG)

// logger('org.hibernate.SQL', DEBUG)
// logger 'org.hibernate.type', TRACE
logger('org.grails.spring.beans.factory.OptimizedAutowireCapableBeanFactory', ERROR)
logger('org.springframework.context.support.PostProcessorRegistrationDelegate', WARN)
logger('org.hibernate.cache.ehcache.AbstractEhcacheRegionFactory', ERROR)
logger 'org.hibernate.tool.schema.internal.ExceptionHandlerLoggedImpl', ERROR
logger 'org.hibernate.engine.jdbc.spi.SqlExceptionHelper', ERROR

logger 'org.springframework.mock.web.MockServletContext', ERROR


class HibernateMappingFilter extends Filter<ILoggingEvent> {

    @Override
    FilterReply decide(ILoggingEvent event) {
        event.message ==~ /.*Specified config option \[importFrom\].*/ ? FilterReply.DENY : FilterReply.NEUTRAL
    }
}

class HibernateDeprecationFilter extends Filter<ILoggingEvent> {

    @Override
    FilterReply decide(ILoggingEvent event) {
        event.message ==~ /HHH90000022.*/ ? FilterReply.DENY : FilterReply.NEUTRAL
    }
}