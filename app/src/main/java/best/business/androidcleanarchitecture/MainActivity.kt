package best.business.androidcleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import best.business.androidcleanarchitecture.presentation.view.UserListScreen
import best.business.androidcleanarchitecture.presentation.viewmodel.GithubUserViewModel
import best.business.androidcleanarchitecture.ui.theme.AndroidCleanArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidCleanArchitectureTheme {
                val githubUserViewModel = hiltViewModel<GithubUserViewModel>()
                UserListScreen(viewModel = githubUserViewModel)
            }
        }
    }
}
