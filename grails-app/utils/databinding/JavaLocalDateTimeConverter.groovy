package databinding

import grails.databinding.converters.ValueConverter

import java.time.LocalDateTime
import java.time.OffsetDateTime

/**
 * @since 22/09/2015
 */
class JavaLocalDateTimeConverter implements ValueConverter {
    @Override
    boolean canConvert(Object value) {
        value instanceof String
    }

    @Override
    Object convert(Object value) {
        LocalDateTime.parse(value)
    }

    @Override
    Class<?> getTargetType() {
        LocalDateTime
    }
}
