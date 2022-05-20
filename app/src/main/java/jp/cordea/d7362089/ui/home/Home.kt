package jp.cordea.d7362089.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.cordea.d7362089.ui.theme.D7362089Theme

@Composable
@ExperimentalMaterial3Api
fun Home(viewModel: HomeViewModel) {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
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
            item { SectionLabel() }
            item { Pickup() }
            item { SectionLabel() }
            item { Carousel() }
            item { SectionLabel() }
            item { Carousel() }
        }
    }
}

@Composable
private fun SectionLabel() {
    Text(
        // TODO
        "example",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
private fun Pickup() {
    Image(
        // TODO
        painter = ColorPainter(color = Color(0xff000000)),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.aspectRatio(19f / 10f)
    )
}

@Composable
private fun Carousel() {
    LazyRow(
        contentPadding = PaddingValues(start = 16.dp, end = 8.dp)
    ) {
        items(10) { CarouselItem() }
    }
}

@Composable
private fun CarouselItem() {
    Column(
        modifier = Modifier
            .width(120.dp)
            .padding(end = 8.dp)
    ) {
        Image(
            // TODO
            painter = ColorPainter(color = Color(0xff000000)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "example",
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
