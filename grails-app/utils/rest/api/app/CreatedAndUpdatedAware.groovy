package rest.api.app

import java.time.OffsetDateTime

/**
 * @since 20/11/2017
 */
trait CreatedAndUpdatedAware {
    OffsetDateTime dateCreated
    OffsetDateTime lastUpdated
}