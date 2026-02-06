package moe.ouom.neriplayer.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import moe.ouom.neriplayer.ui.BlurTransformation

@Composable
fun CoverBlurBackground(
    coverUrl: String?,
    modifier: Modifier = Modifier,
    blur: Float = 18f,
    alpha: Float = 0.55f
) {
    if (coverUrl.isNullOrBlank()) return

    val context = LocalContext.current
    val imageRequest = remember(coverUrl, blur) {
        ImageRequest.Builder(context)
            .data(coverUrl)
            .crossfade(false)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .networkCachePolicy(CachePolicy.ENABLED)
            .transformations(
                if (blur > 0f) listOf(BlurTransformation(context, radius = blur)) else emptyList()
            )
            .build()
    }

    AsyncImage(
        model = imageRequest,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
            .alpha(alpha)
    )
}

