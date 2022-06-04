package jp.cordea.d7362089

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.d7362089.ui.NavGraph
import jp.cordea.d7362089.ui.theme.D7362089Theme

@AndroidEntryPoint
@ExperimentalCoilApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            D7362089Theme {
                NavGraph()
            }
        }
    }
}
