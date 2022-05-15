package jp.cordea.d7362089

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.d7362089.ui.theme.D7362089Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            D7362089Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainList()
                }
            }
        }
    }
}

@Composable
private fun MainList() {
    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
        item { SectionLabel() }
        item { Pickup() }
        item { SectionLabel() }
        item { Carousel() }
        item { SectionLabel() }
        item { Carousel() }
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
fun DefaultPreview() {
    D7362089Theme {
        MainList()
    }
}
