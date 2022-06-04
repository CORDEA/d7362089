package jp.cordea.d7362089.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
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
    Scaffold(
        modifier = Modifier.nestedScroll(scrollConnection),
        topBar = {
            Box(
                modifier = Modifier
                    .height(with(LocalDensity.current) { appBarHeight.value.toDp() })
            ) {
                Image(
                    // TODO
                    painter = ColorPainter(Color.Cyan),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
                SmallTopAppBar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    title = { Text("") },
                )
            }
        }
    ) {
        LazyColumn(
            contentPadding = it
        ) {
        }
    }
}
