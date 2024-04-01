package apps.sumit.apitest.features.domain.use_case

import apps.sumit.apitest.common.Resource
import apps.sumit.apitest.di.Realm
import apps.sumit.apitest.di.Remote
import apps.sumit.apitest.features.domain.model.NewsList
import apps.sumit.apitest.features.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    @Remote private val repository: NewsRepository,
) {
    operator fun invoke(q: String): Flow<Resource<NewsList>> = flow {
        var newsList: NewsList?
        try {
            emit(Resource.Loading<NewsList>())
            newsList = repository.search(q)
            emit(Resource.Success<NewsList>(newsList, true))
        } catch (e: Exception) {
            emit(Resource.Error<NewsList>("Error"))
        }
    }
}