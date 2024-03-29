package apps.sumit.apitest.features.domain.repository

import apps.sumit.apitest.features.domain.model.NewsList

interface NewsRepository {
    suspend fun getBreakingNews(): NewsList
    suspend fun search(q: String): NewsList
}