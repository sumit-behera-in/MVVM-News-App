package apps.sumit.apitest.features.data.remote.repository

import apps.sumit.apitest.features.data.remote.NewsApi
import apps.sumit.apitest.features.data.remote.dto.toNewsList
import apps.sumit.apitest.features.domain.model.NewsList
import apps.sumit.apitest.features.domain.repository.NewsRepository

class NewsRepoRemote(private val api: NewsApi) : NewsRepository {
    override suspend fun getBreakingNews(): NewsList {
        return api.getBreakingNews().toNewsList()
    }

    override suspend fun search(q: String): NewsList {
        return api.search(q).toNewsList()
    }
}