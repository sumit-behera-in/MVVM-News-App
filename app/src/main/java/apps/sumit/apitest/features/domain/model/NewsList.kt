package apps.sumit.apitest.features.domain.model

data class NewsList(
    var type: String = "Breaking news",
    val newsList: List<News>,
)

