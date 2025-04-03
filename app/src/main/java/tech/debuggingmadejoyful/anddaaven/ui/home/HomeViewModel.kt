package tech.debuggingmadejoyful.anddaaven.ui.home

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tech.debuggingmadejoyful.anddaaven.AndDaavenApplication.Companion.ANDDAAVEN_APP_URI
import tech.debuggingmadejoyful.anddaaven.TefillaUri
import tech.debuggingmadejoyful.anddaaven.data.TefillaType
import java.util.UUID

/**
 * UI state for the Home route.
 *
 * This is derived from [HomeViewModelState], but split into two possible subclasses to more
 * precisely represent the state available to render the UI.
 */
data class HomeUiState(

    val isLoading: Boolean,
    val errorMessages: List<String>,
    val searchInput: String,

)

/**
 * An internal representation of the Home route state, in a raw form
 */
private data class HomeViewModelState(

    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList(),
    val searchInput: String = "",
) {

    /**
     * Converts this [HomeViewModelState] into a more strongly typed [HomeUiState] for driving
     * the ui.
     */
    fun toUiState(): HomeUiState =
        HomeUiState(
                isLoading = isLoading,
                errorMessages = errorMessages,
                searchInput = searchInput
            )
}

/**
 * ViewModel that handles the business logic of the Home screen
 */
class HomeViewModel(
    private val navController: NavController
) : ViewModel() {

    private val viewModelState = MutableStateFlow(
        HomeViewModelState(
            isLoading = true,
        )
    )

    // UI state exposed to the UI
    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        // TODO I think things like date etc will be set up here
    }

    /**
     * Notify that an error was displayed on the screen
     */
    fun errorShown(errorId: Long) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages
            currentUiState.copy(errorMessages = errorMessages)
        }
    }

    fun navigateToTefilla(tefillaType: TefillaType) {

            navController.navigate(route = TefillaUri(tefillaType))

    }

    companion object {
        fun provideFactory(
            navController: NavController
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(navController) as T
            }
        }
    }
}