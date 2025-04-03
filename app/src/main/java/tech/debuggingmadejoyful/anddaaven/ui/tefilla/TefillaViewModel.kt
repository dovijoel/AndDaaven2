package tech.debuggingmadejoyful.anddaaven.ui.tefilla

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tech.debuggingmadejoyful.anddaaven.data.Tefilla
import tech.debuggingmadejoyful.anddaaven.data.TefillaRepository
import tech.debuggingmadejoyful.anddaaven.data.TefillaType
import tech.debuggingmadejoyful.anddaaven.data.Result
import java.util.UUID

data class TefillaUiState(
    val tefilla: Tefilla? = null,
    val tefillaType: TefillaType,
    val loading: Boolean = false,
    val error: String? = null
)

class TefillaViewModel(
    private val tefillaRepository: TefillaRepository,
    tefillaType: TefillaType
) : ViewModel() {
    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(TefillaUiState(loading = true, tefillaType = tefillaType))
    val uiState: StateFlow<TefillaUiState> = _uiState.asStateFlow()

    init {
        getTefilla(tefillaType)
    }
    private fun getTefilla(type: TefillaType) {
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            val tefilla =  tefillaRepository.getTefilla(type)
            _uiState.update {
                when (tefilla) {
                    is Result.Success -> it.copy(tefilla = tefilla.data, loading = false)
                    is Result.Error -> {
                        it.copy(error = "error", loading = false)
                    }
                }
            }
        }
    }

    companion object {
        fun provideFactory(
            tefillaRepository: TefillaRepository,
            tefillaType: TefillaType
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TefillaViewModel(tefillaRepository, tefillaType) as T
            }
        }
    }
}