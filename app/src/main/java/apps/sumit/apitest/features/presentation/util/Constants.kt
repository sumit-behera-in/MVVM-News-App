package apps.sumit.apitest.features.presentation.util

import apps.sumit.apitest.R

object Constants {
    val bottomMenuContents: List<BottomMenuContent> = listOf(
        BottomMenuContent(title = "Home", iconId = R.drawable.ic_home),
        BottomMenuContent(title = "Finance", iconId = R.drawable.ic_finance),
        BottomMenuContent(title = "Technology", iconId = R.drawable.ic_technology),
        BottomMenuContent(title = "Search", iconId = R.drawable.ic_search),
        BottomMenuContent(title = "Game", iconId = R.drawable.ic_game),
        BottomMenuContent(title = "Entertainment", iconId = R.drawable.ic_entertainment)
    )

    val screenList: List<String> = listOf(
        "Breaking News",
        "Finance News",
        "Technology News",
        "Search News",
        "Gaming News",
        "Entertainment News"
    )
}