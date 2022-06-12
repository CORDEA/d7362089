package jp.cordea.d7362089.api.response

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Serializable
data class PhotoResponse(
    val id: String,
    val user: User,
    @SerialName("created_at")
    @Serializable(with = DateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @SerialName("updated_at")
    @Serializable(with = DateTimeSerializer::class)
    val updatedAt: LocalDateTime,
    val width: Int,
    val height: Int,
    val color: String,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val description: String?,
    @SerialName("alt_description")
    val altDescription: String?,
    val exif: Exif,
    val location: Location,
    val urls: Urls,
    val links: Links
) {
    @Serializable
    data class Exif(
        val make: String?,
        val model: String?
    )

    @Serializable
    data class Location(
        val name: String?
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

class DateTimeSerializer : KSerializer<LocalDateTime> {
    private val serializer = String.serializer()

    override val descriptor: SerialDescriptor
        get() = serializer.descriptor

    override fun deserialize(decoder: Decoder): LocalDateTime {
        return LocalDateTime.parse(
            decoder.decodeString(),
            DateTimeFormatter.ISO_OFFSET_DATE_TIME
        )
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        throw NotImplementedError()
    }
}
