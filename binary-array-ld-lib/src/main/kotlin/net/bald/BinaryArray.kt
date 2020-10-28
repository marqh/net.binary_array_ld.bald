package net.bald

/**
 * Represents the metadata of a binary array dataset.
 * See https://www.opengis.net/def/binary-array-ld/Array
 */
interface BinaryArray {
    /**
     * The URI which identifies the dataset.
     */
    val uri: String

    /**
     * The root container.
     */
    val root: Container
}