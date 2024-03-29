package apps.sumit.apitest.features.domain.use_case

import apps.sumit.apitest.common.Resource
import apps.sumit.apitest.di.Realm
import apps.sumit.apitest.di.Remote
import apps.sumit.apitest.features.domain.model.NewsList
import apps.sumit.apitest.features.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBreakingNewsUseCase @Inject constructor(
    @Remote private val repository: NewsRepository,
    @Realm private val realmRepo: NewsRepository,
) {
    operator fun invoke(): Flow<Resource<NewsList>> = flow {
        var newsList: NewsList?
        try {
            emit(Resource.Loading<NewsList>())
            newsList = repository.getBreakingNews()
            emit(Resource.Success<NewsList>(newsList, flag = true))
        } catch (e: Exception) {
            // io error
            newsList = realmRepo.getBreakingNews()
            emit(Resource.Success<NewsList>(newsList, flag = false))
        }
    }
}