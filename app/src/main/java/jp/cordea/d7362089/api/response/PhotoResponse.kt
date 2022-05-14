package jp.cordea.d7362089.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    val id: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    val width: Int,
    val height: Int,
    val color: String,
    val downloads: Int,
    val likes: Int,
    val description: String,
    val exif: Exif,
    val location: Location,
    val urls: Urls,
    val links: Links
) {
    @Serializable
    data class Exif(
        val make: String,
        val model: String
    )

    @Serializable
    data class Location(
        val name: String
    )

    @Serializable
    data class Urls(
        val raw: String,
        val full: String,
        val regular: String,
        val thumb: String
    )

    @Serializable
    data class Links(
        val html: String
    )

    @Serializable
    data class User(
        val id: String,
        val name: String,
        val links: Links
    )
}
