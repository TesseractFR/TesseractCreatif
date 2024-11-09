package onl.tesseract.persistence

/**
 * Abstract persistence layer to read and write entities from a data store
 */
interface Repository<T, ID> {

    /**
     * Find an entity by its id
     * @return The entity mapped to this id, null if not found
     */
    fun getById(id: ID): T?

    /**
     * Persist the entity
     */
    fun save(entity: T)

}