package jp.cordea.d7362089.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import jp.cordea.d7362089.ui.theme.D7362089Theme
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
fun Details(viewModel: DetailsViewModel) {
    val appBarMaxHeight = with(LocalDensity.current) { 250.dp.toPx() }
    val appBarMinHeight = with(LocalDensity.current) { 64.dp.toPx() }
    val appBarHeight = remember { mutableStateOf(appBarMaxHeight) }
    val scrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                appBarHeight.value =
                    (appBarHeight.value + available.y).coerceIn(appBarMinHeight, appBarMaxHeight)
                return Offset.Zero
            }
        }
    }
    val thumbnail by viewModel.thumbnail.observeAsState()
    val description by viewModel.description.observeAsState()
    val userName by viewModel.userName.observeAsState()
    val width by viewModel.width.observeAsState()
    val height by viewModel.height.observeAsState()
    val createdAt by viewModel.createdAt.observeAsState()
    val downloads by viewModel.downloads.observeAsState()
    val location by viewModel.location.observeAsState()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollConnection),
        topBar = {
            Box(
                modifier = Modifier
                    .height(with(LocalDensity.current) { appBarHeight.value.toDp() })
            ) {
                Image(
                    painter = rememberImagePainter(data = thumbnail),
                    contentDescription = description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                SmallTopAppBar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    title = { Text("By $userName") },
                )
            }
        }
    ) {
        LazyColumn(
            contentPadding = it,
            modifier = Modifier.padding(16.dp),
        ) {
            item {
                Text(description.orEmpty())
            }
            item { Divider(modifier = Modifier.padding(vertical = 8.dp)) }
            item {
                ListItem(title = "Size", content = "$width x $height")
            }
            createdAt?.let {
                item {
                    ListItem(
                        title = "Created date",
                        content = it.format(
                            DateTimeFormatter.ofLocalizedDateTime(
                                FormatStyle.LONG,
                                FormatStyle.SHORT
                            )
                        )
                    )
                }
            }
            item {
                ListItem(title = "Downloads", content = "$downloads")
            }
            item {
                ListItem(title = "Location", content = location.orEmpty())
            }
        }
    }
}

@Composable
private fun ListItem(title: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(64.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            content,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    D7362089Theme {
        Column(modifier = Modifier.padding(16.dp)) {
            ListItem("title", "content")
        }
    }
}
