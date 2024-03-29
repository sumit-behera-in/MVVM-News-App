package apps.sumit.apitest.features.data.remote.dto

import apps.sumit.apitest.features.domain.model.NewsList

data class ApiResponseDto(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,
)

fun ApiResponseDto.toNewsList(): NewsList {
    return NewsList(
        newsList = articles.map {
            it.toNews()
        }
    )
}