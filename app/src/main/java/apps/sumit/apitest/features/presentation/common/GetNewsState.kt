package apps.sumit.apitest.features.presentation.common

import apps.sumit.apitest.features.domain.model.NewsList

data class GetNewsState(
    val isLoading: Boolean = false,
    val newsList: NewsList? = null,
    val error: String = "",
)