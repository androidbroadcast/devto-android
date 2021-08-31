package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListingDto(
    val typeOf: String,
    val id: String,
    val title: String,
    val slug: String,
    val body: MarkdownDto,
    val tagList: String,
    val tags: List<String>,
    val category: Category,
    val processedHtml: String,
    val published: Boolean,
    val user: SharedUserDto,
    val organization: SharedOrganizationDto,
) {

    @Serializable
    enum class Category {
        @SerialName("cfp")
        CFP,

        @SerialName("forhire")
        FORHIRE,

        @SerialName("collabs")
        COLLABS,

        @SerialName("education")
        EDUCATION,

        @SerialName("jobs")
        JOBS,

        @SerialName("mentors")
        MENTORS,

        @SerialName("products")
        PRODUCTS,

        @SerialName("mentees")
        MENTEES,

        @SerialName("forsale")
        FORSALE,

        @SerialName("misc")
        MISC,
    }
}
