package jp.cordea.d7362089.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import jp.cordea.d7362089.ui.theme.D7362089Theme

@Composable
@ExperimentalCoilApi
@ExperimentalMaterial3Api
fun Home(viewModel: HomeViewModel) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    val items by viewModel.items.observeAsState(emptyMap())
    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = { Text("Home") },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        LazyColumn(contentPadding = it) {
            item { SectionLabel("Pickup") }
            item { Pickup(viewModel) }
            items.map {
                item { SectionLabel(it.key.toString()) }
                item { Carousel(viewModels = it.value) }
            }
        }
    }
}

@Composable
private fun SectionLabel(title: String) {
    Text(
        title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
@ExperimentalCoilApi
private fun Pickup(viewModel: HomeViewModel) {
    val thumbnail by viewModel.pickupThumbnail.observeAsState("")
    Image(
        painter = rememberImagePainter(data = thumbnail),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.aspectRatio(19f / 10f)
    )
}

@Composable
@ExperimentalCoilApi
private fun Carousel(viewModels: List<HomeItemViewModel>) {
    LazyRow(
        contentPadding = PaddingValues(start = 16.dp, end = 8.dp)
    ) {
        viewModels.map {
            item { CarouselItem(it) }
        }
    }
}

@Composable
@ExperimentalCoilApi
private fun CarouselItem(viewModel: HomeItemViewModel) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(end = 8.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = viewModel.thumbnail),
            contentDescription = viewModel.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            viewModel.title,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalMaterial3Api
fun DefaultPreview() {
    D7362089Theme {
//        Home(HomeViewModel())
    }
}
