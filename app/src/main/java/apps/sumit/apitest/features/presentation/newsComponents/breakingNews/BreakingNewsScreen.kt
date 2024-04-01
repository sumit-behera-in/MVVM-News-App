package apps.sumit.apitest.features.presentation.newsComponents.breakingNews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import apps.sumit.apitest.R
import apps.sumit.apitest.features.presentation.common.SingleNews
import apps.sumit.apitest.features.presentation.newsComponents.breakingNews.viewmodel.BreakingNewsViewModel
import apps.sumit.apitest.features.presentation.ui.theme.DeepBlue
import apps.sumit.apitest.features.presentation.ui.theme.TextWhite

@Composable
fun BreakingNewsScreen(
    modifier: Modifier = Modifier,
    viewModel: BreakingNewsViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {
    val state = viewModel.state.value

    LaunchedEffect(true) {
        if (viewModel.changed.value) {
            viewModel.changed.value = false
            viewModel.getNews()
        }
    }
    if (state.isLoading) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(DeepBlue)
        ) {
            state.newsList?.newsList?.let { newsList ->
                items(newsList.size) { i ->
                    if (state.newsList.newsList[i].title != "[Removed]")
                        SingleNews(
                            news = state.newsList.newsList[i],
                            navHostController = navHostController
                        )
                }
            }
        }
    }
}