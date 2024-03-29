package apps.sumit.apitest.features.domain.use_case

import apps.sumit.apitest.common.Resource
import apps.sumit.apitest.di.Realm
import apps.sumit.apitest.di.Remote
import apps.sumit.apitest.features.domain.model.NewsList
import apps.sumit.apitest.features.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    @Remote private val repository: NewsRepository,
    @Realm private val realmRepo: NewsRepository,
) {
    operator fun invoke(q: String): Flow<Resource<NewsList>> = flow {
        try {
            emit(Resource.Loading<NewsList>())
            val newsList: NewsList = repository.search(q)
            emit(Resource.Success<NewsList>(newsList, true))
        } catch (e: HttpException) {
            // unsuccessful api call
            emit(Resource.Error<NewsList>(e.localizedMessage ?: "An unexpected error occurred"))

        } catch (e: IOException) {
            // io error
            emit(Resource.Error<NewsList>(e.localizedMessage ?: "No Internet Connection"))
        }
    }
}