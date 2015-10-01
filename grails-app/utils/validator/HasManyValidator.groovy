package validator

/**
 * @since 01/10/2015
 */
class HasManySizeValidator {

    Integer minSize
    Integer maxSize

    HasManySizeValidator(int minSize) {
        this.minSize = minSize
    }

    HasManySizeValidator(int minSize, int maxSize) {
        this.maxSize = maxSize
        this.minSize = minSize
    }

    HasManySizeValidator(Range<Integer> size) {
        this.minSize = size.from
        this.maxSize = size.to
    }

    Object isValid(Set value) {
        if (minSize && (!value || value.size() < minSize)) return ['validation.hasmany.size.atleast', minSize]
        if (maxSize && value.size() > maxSize) return ['validation.hasmany.size.atmost', maxSize]
        return true
    }
}