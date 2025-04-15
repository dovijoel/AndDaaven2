package tech.debuggingmadejoyful.anddaaven.ui.preferences

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tech.debuggingmadejoyful.anddaaven.data.preferences.PreferencesRepository
import tech.debuggingmadejoyful.anddaaven.data.tefilla.Result

data class PreferencesUiState(
    val currentTheme: String? = null
)

class PreferencesViewModel(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(PreferencesUiState())
    val uiState: StateFlow<PreferencesUiState> = _uiState.asStateFlow()

    init {
        updateSettings()
    }

    suspend fun updateTheme(theme: String) {
        preferencesRepository.saveTheme(theme)
    }

    private fun updateSettings() {
        viewModelScope.launch {
            val currentTheme = preferencesRepository.currentTheme.last()
            _uiState.update {
                it.copy(
                    currentTheme = currentTheme
                )
            }
        }
    }

    companion object {
        fun provideFactory(
            preferencesRepository: PreferencesRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PreferencesViewModel(preferencesRepository) as T
            }
        }
    }
}