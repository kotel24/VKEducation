package ru.sumin.vkeducation.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.sumin.vkeducation.domain.applist.GetAppsListUseCase
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val getAppsListUseCase: GetAppsListUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<AppListUiState>(
        AppListUiState.Loading
    )

    val uiState = _uiState.asStateFlow()

    private val _events = Channel<AppsListEvent>(Channel.Factory.BUFFERED)

    val events = _events.receiveAsFlow()

    init {
        getAppsList()
    }

    fun onLogoClick() {
        _events.trySend(
            AppsListEvent.ShowSnackbar("Логотип RuStore нажат")
        )
    }

    fun getAppsList() {
        viewModelScope.launch {
            _uiState.value = AppListUiState.Loading

            runCatching {
                val appsList = getAppsListUseCase()

                _uiState.value = AppListUiState.Content(
                    appsList = appsList,
                )
            }.onFailure {
                _uiState.value = AppListUiState.Error
            }
        }
    }
}