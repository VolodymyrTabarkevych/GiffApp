package ua.tabarkevych.composemvi.presentation.gifs.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import ua.tabarkevych.composemvi.R
import ua.tabarkevych.composemvi.domain.model.GifPost
import ua.tabarkevych.composemvi.presentation.gifs.GifsContract
import ua.tabarkevych.composemvi.ui.dimens.spacing
import ua.tabarkevych.composemvi.ui.dimens.width
import ua.tabarkevych.composemvi.util.EMPTY_STRING

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
                items.loadState.refresh is LoadState.Error -> {
                    val e = items.loadState.refresh as LoadState.Error
                    setEvent(
                        GifsContract.Event.OnGifsError(
                            e.error.localizedMessage ?: EMPTY_STRING
                        )
                    )
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillParentMaxSize()
                        ) {
                            Button(onClick = { refresh() }) {
                                Text(text = stringResource(id = R.string.common_refresh))
                            }
                        }
                    }
                }
                items.loadState.append is LoadState.Error -> {
                    val e = items.loadState.append as LoadState.Error
                    setEvent(
                        GifsContract.Event.OnGifsError(
                            e.error.localizedMessage ?: EMPTY_STRING
                        )
                    )
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(MaterialTheme.spacing.medium)
                        ) {
                            Button(onClick = { retry() }) {
                                Text(text = stringResource(id = R.string.common_retry))
                            }
                        }
                    }
                }
            }
        }
    }
}