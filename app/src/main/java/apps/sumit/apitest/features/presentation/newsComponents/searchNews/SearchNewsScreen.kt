package apps.sumit.apitest.features.presentation.newsComponents.searchNews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import apps.sumit.apitest.R
import apps.sumit.apitest.features.presentation.common.SingleNews
import apps.sumit.apitest.features.presentation.newsComponents.searchNews.viewmodel.SearchNewsViewModel
import apps.sumit.apitest.features.presentation.ui.theme.DeepBlue

@Composable
fun SearchNewsScreen(
    modifier: Modifier = Modifier,
    parentNavHostController: NavHostController,
    viewModel: SearchNewsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
//    var newsList by remember {
//        mutableStateOf(state.newsList)
//    }
    val text = remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(DeepBlue),
        topBar = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DeepBlue)
            ) {
                TextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    placeholder = { Text("Enter Your Text") },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (text.value.text.isNotBlank()) {
                                viewModel.getNews(text.value.text)
                            }
                            text.value = TextFieldValue("")
                        }
                )
            }
        }
    ) {

        if (state.isLoading) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(DeepBlue)
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(it)
                    .fillMaxSize()
                    .fillMaxHeight()
            ) {
                state.newsList?.newsList?.let { it ->
                    items(it.size) { i ->
                        if (state.newsList.newsList[i].title != "[Removed]")
                            SingleNews(
                                news = state.newsList.newsList[i],
                                navHostController = parentNavHostController
                            )
                    }
                }
            }
        }

    }
}