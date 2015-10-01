package databinding

import grails.databinding.converters.ValueConverter

import java.time.LocalDate

/**
 * @since 02/09/2015
 */
class JavaLocalDateConverter implements ValueConverter {
    @Override
    boolean canConvert(Object value) {
        value instanceof String
    }

    @Override
    Object convert(Object value) {
        LocalDate.parse(value)
    }

    @Override
    Class<?> getTargetType() {
        LocalDate
    }
}
