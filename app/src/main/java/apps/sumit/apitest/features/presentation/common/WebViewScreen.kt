package apps.sumit.apitest.features.presentation.common

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import java.net.URLDecoder

@Composable
fun WebViewScreen(
    modifier: Modifier,
    mUrl: String,
) {
    val url = URLDecoder.decode(mUrl)

    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)

        ) {
            AndroidView(
                factory = {
                    WebView(it).apply {
                        settings.javaScriptEnabled
                        settings.domStorageEnabled
                        settings.useWideViewPort
                        settings.loadWithOverviewMode
                        settings.allowFileAccess

                        computeScroll()

                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        loadUrl(url)
                    }
                }, update = {
                    it.loadUrl(url)
                })

        }
    }
}
