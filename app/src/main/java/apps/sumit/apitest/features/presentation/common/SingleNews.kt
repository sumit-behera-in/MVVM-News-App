package apps.sumit.apitest.features.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import apps.sumit.apitest.R
import apps.sumit.apitest.features.domain.model.News
import apps.sumit.apitest.features.presentation.util.Screen
import coil.compose.AsyncImage
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun SingleNews(
    modifier: Modifier = Modifier,
    news: News,
    navHostController: NavHostController,
) {
    Card(
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val encodedUrl = URLEncoder.encode(
                    news.url,
                    StandardCharsets.UTF_8.toString()
                )
                navHostController.navigate(route = Screen.WebViewScreen.route + "/url=${encodedUrl}")
            }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = news.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                placeholder = painterResource(R.drawable.dummy_photo)
            )
            news.title?.let { Text(text = it, fontSize = 20.sp) }
            Spacer(modifier = Modifier.height(5.dp))
            news.description?.let { Text(text = it) }

        }
    }
}