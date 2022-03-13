package ua.tabarkevych.composemvi.presentation.gifs.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import ua.tabarkevych.composemvi.domain.model.GifPost
import ua.tabarkevych.composemvi.presentation.gifs.GifsContract
import ua.tabarkevych.composemvi.ui.dimens.spacing
import ua.tabarkevych.composemvi.ui.dimens.width

@ExperimentalCoilApi
@Composable
fun GifsList(
    items: LazyPagingItems<GifPost>,
    setEvent: (event: GifsContract.Event) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = items
        ) { item ->
            item?.let {
                GifItem(
                    item = it,
                    setEvent = setEvent
                )
            }
        }
        items.apply {
            when {
                items.loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillParentMaxSize()
                        ) {
                            CircularProgressIndicator(strokeWidth = MaterialTheme.width.medium)
                        }
                    }
                }
                items.loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(MaterialTheme.spacing.medium)
                        ) {
                            CircularProgressIndicator(strokeWidth = MaterialTheme.width.medium)
                        }
                    }
                }
            }
        }
    }
}