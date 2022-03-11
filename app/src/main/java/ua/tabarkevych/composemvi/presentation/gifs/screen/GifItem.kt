package ua.tabarkevych.composemvi.presentation.gifs.screen

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import ua.tabarkevych.composemvi.R
import ua.tabarkevych.composemvi.domain.model.GifPost
import ua.tabarkevych.composemvi.presentation.gifs.GifsContract
import ua.tabarkevych.composemvi.ui.dimens.spacing
import ua.tabarkevych.composemvi.ui.dimens.textSize
import ua.tabarkevych.composemvi.ui.theme.BlackSqueeze

@ExperimentalCoilApi
@Composable
fun GifItem(item: GifPost, setEvent: (event: GifsContract.Event) -> Unit) {
    val context = LocalContext.current
    val gifHeight = item.height.toInt().dp
    val gif = rememberImagePainter(
        data = item.avatarUrl,
        imageLoader = ImageLoader.Builder(context)
            .placeholder(R.drawable.ic_baseline_person_24)
            .crossfade(true)
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder(context))
                } else {
                    add(GifDecoder())
                }
            }
            .build()
    )

    Column(
        Modifier
            .padding(top = MaterialTheme.spacing.small)
    ) {
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(
                    bottom = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.medium
                )
        ) {
            Image(
                painter = gif,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(modifier = Modifier.padding(start = MaterialTheme.spacing.small)) {
                Text(
                    text = buildAnnotatedString {
                        append("gif by ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                            append(item.username)
                        }
                    },
                    fontSize = MaterialTheme.textSize.normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.importDatetime,
                    fontSize = MaterialTheme.textSize.x_small,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Image(
            modifier = Modifier
                .height(gifHeight)
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.small)
                .background(BlackSqueeze)
                .clickable(onClick = {
                    setEvent(GifsContract.Event.OnGifClicked(item.originalImageUrl))
                }),
            painter = rememberImagePainter(
                data = item.fixedHeightImageUrl,
                imageLoader = ImageLoader.Builder(context)
                    .placeholder(R.drawable.ic_baseline_gif_24)
                    .crossfade(true)
                    .componentRegistry {
                        if (Build.VERSION.SDK_INT >= 28) {
                            add(ImageDecoderDecoder(context))
                        } else {
                            add(GifDecoder())
                        }
                    }
                    .build()
            ),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun GifPreview() {
    GifItem(
        item = GifPost(
            id = "1",
            username = "",
            title = "MEME",
            importDatetime = "04.03.2022",
            originalImageUrl = "",
            fixedHeightImageUrl = "",
            height = "",
            avatarUrl = ""
        )
    ) {}
}