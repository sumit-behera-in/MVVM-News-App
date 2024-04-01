package apps.sumit.apitest.features.presentation.newsComponents.searchNews.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apps.sumit.apitest.common.Resource
import apps.sumit.apitest.features.domain.model.NewsList
import apps.sumit.apitest.features.domain.use_case.SearchUseCase
import apps.sumit.apitest.features.presentation.common.GetNewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(GetNewsState())
    val state = _state

    fun getNews(query: String) {
        searchUseCase(query).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = GetNewsState(
                        newsList = result.data?.let {
                            NewsList(
                                type = query,
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