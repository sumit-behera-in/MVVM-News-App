package apps.sumit.apitest.features.presentation.newsComponents.breakingNews.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apps.sumit.apitest.common.Resource
import apps.sumit.apitest.features.data.db.RealmModel
import apps.sumit.apitest.features.domain.model.NewsList
import apps.sumit.apitest.features.domain.use_case.GetBreakingNewsUseCase
import apps.sumit.apitest.features.presentation.common.GetNewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BreakingNewsViewModel @Inject constructor(
    private val getBreakingNewsUseCase: GetBreakingNewsUseCase,
    private val realm: Realm,
) : ViewModel() {
    private val _state = mutableStateOf(GetNewsState())
    val state = _state

    init {
        getNews()
    }

    private fun getNews() {
        getBreakingNewsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.flag == true) {

                        realm.writeBlocking {
                            // Selected by a query
                            val query = this.query<RealmModel>("type = $0", "Breaking news")
                            delete(query)
                        }

                        realm.write {
                            result.data?.newsList?.forEach {
                                val news = RealmModel().apply {
                                    type = result.data.type
                                    name = it.name
                                    title = it.title
                                    url = it.url
                                    urlToImage = it.urlToImage
                                    content = it.content
                                    description = it.description
                                }
                                copyToRealm(news)
                            }
                        }
                    }
                    _state.value = GetNewsState(
                        newsList = result.data?.let {
                            NewsList(
                                type = "Breaking news",
                                newsList = it.newsList
                            )
                        }
                    )

                }

                is Resource.Loading -> {
                    _state.value = GetNewsState(isLoading = true)
                }

                is Resource.Error -> {

                    _state.value =
                        GetNewsState(error = result.message ?: "Unknown error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}