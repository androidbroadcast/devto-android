package dev.androidbroadcast.devto.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.androidbroadcast.devto.api.result.Result
import dev.androidbroadcast.devto.user.model.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
internal class UserViewModel @Inject constructor(
    private val userUseCase: Provider<CurrentUserUseCase>
) : ViewModel() {

    val user: StateFlow<Result<User>?> = flow {
        val useCase = userUseCase.get()
        emit(useCase())
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)
}
