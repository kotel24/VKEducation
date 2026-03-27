package ru.sumin.vkeducation.presentation.appdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.sumin.vkeducation.domain.appdetails.GetAppDetailsUseCase
import ru.sumin.vkeducation.domain.appdetails.ObserveAppDetailsUseCase
import ru.sumin.vkeducation.domain.appdetails.ToggleWishlistUseCase
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
    private val observeAppDetailsUseCase: ObserveAppDetailsUseCase,
    private val toggleWishlistUseCase: ToggleWishlistUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val id: String = checkNotNull(savedStateHandle["id"])

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppDetailsEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        observeAppDetails()
        getAppDetails()
    }

    fun refresh(){
        getAppDetails()
    }

    private fun observeAppDetails() {
        viewModelScope.launch {
            observeAppDetailsUseCase(id)
                .catch { _state.value = AppDetailsState.Error }
                .collect { appDetails ->
                    _state.value = AppDetailsState.Content(
                        appDetails = appDetails,
                        descriptionCollapsed = false,
                    )
                }
        }
    }

    fun toggleWishlist(){
        viewModelScope.launch {
            toggleWishlistUseCase(id)
        }
    }

    fun showUnderDevelopmentMessage() {
        viewModelScope.launch {
            _events.send(AppDetailsEvent.UnderDevelopment)
        }
    }

    fun collapseDescription() {
        _state.update { currentState ->
            if (currentState is AppDetailsState.Content) {
                currentState.copy(descriptionCollapsed = true)
            } else {
                currentState
            }
        }
    }

    private fun getAppDetails() {
        viewModelScope.launch {
            _state.value = AppDetailsState.Loading
            try {
                getAppDetailsUseCase(id = id)
            } catch (e: Exception) {
                _state.value = AppDetailsState.Error
            }
        }
    }
}