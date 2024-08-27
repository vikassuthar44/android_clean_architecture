package best.business.androidcleanarchitecture.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

@Composable
inline fun <T> RequestRender(
    state: State<RequestState<T>>,
    onLoading: @Composable () -> Unit = {},
    onSuccess: @Composable (T) -> Unit,
    onError: @Composable (String) -> Unit = {},
) {
    when (val itemValue = state.value) {
        is RequestState.Error -> onError(itemValue.error)
        RequestState.Idle -> {

        }

        RequestState.Loading -> onLoading()
        is RequestState.Success -> {
            itemValue.data?.let {
                onSuccess(it)
            }
        }
    }
}