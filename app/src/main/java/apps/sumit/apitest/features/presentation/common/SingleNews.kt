package apps.sumit.apitest.features.presentation.common

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
import apps.sumit.apitest.R
import apps.sumit.apitest.features.domain.model.News
import coil.compose.AsyncImage

@Composable
fun SingleNews(
    modifier: Modifier = Modifier,
    news: News,
) {
    Card(
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
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