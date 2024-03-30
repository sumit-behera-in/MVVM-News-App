package apps.sumit.apitest.features.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import apps.sumit.apitest.R
import apps.sumit.apitest.features.presentation.ui.theme.DeepBlue
import apps.sumit.apitest.features.presentation.ui.theme.TextWhite

@Composable
fun NewsScreenTopSection(
    text: String = "Breaking News",
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(DeepBlue)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$text",
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp,
                color = TextWhite
            )

        }

        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}
