package apps.sumit.apitest.features.data.remote.dto

import apps.sumit.apitest.features.domain.model.News

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,
)

fun Article.toNews(): News {

    return News(
        name = source.name,
        title = title,
        url = url,
        content = content,
        description = description,
        urlToImage = urlToImage
    )
}

