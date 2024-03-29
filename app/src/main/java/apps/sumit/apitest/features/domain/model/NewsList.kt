package apps.sumit.apitest.features.domain.model

data class NewsList(
    val type: String = "Breaking news",
    val newsList: List<News>,
)

