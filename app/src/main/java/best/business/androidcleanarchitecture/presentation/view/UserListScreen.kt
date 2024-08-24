package best.business.androidcleanarchitecture.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import best.business.androidcleanarchitecture.data.model.remote.UserResponse
import best.business.androidcleanarchitecture.presentation.viewmodel.GithubUserViewModel
import best.business.androidcleanarchitecture.util.ApiResponse
import coil.compose.rememberImagePainter

@Composable
fun UserListScreen(viewModel: GithubUserViewModel) {
    val users by viewModel.users.collectAsState()

    when (users) {
        is ApiResponse.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }
        is ApiResponse.Success -> {
            UserList((users as ApiResponse.Success<List<UserResponse>>).data)
        }
        is ApiResponse.Failure -> {
            Text(
                text = "Error: ${(users as ApiResponse.Failure).exception}",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun UserList(users: List<UserResponse>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(users) { user ->
            UserRow(user = user)
        }
    }
}

@Composable
fun UserRow(user: UserResponse) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = user.avatar_url),
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        user.login?.let { Text(text = it, style = MaterialTheme.typography.titleMedium) }
    }
}
