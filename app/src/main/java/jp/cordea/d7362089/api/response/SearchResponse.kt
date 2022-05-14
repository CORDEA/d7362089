package jp.cordea.d7362089.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val total: Long,
    @SerialName("total_pages")
    val totalPages: Int,
    val results: List<PhotoResponse>
)
