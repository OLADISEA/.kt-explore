import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null
)

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChanged(email: String) {
        _uiState.value = _uiState.value.copy(email = email, emailError = null)
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value.copy(password = password, passwordError = null)
    }

    fun validate(): Boolean {
        var isValid = true
        val currentState = _uiState.value

        val emailError = if (!currentState.email.contains("@")) "Invalid email" else null
        val passwordError = if (currentState.password.length < 6) "Password too short" else null

        if (emailError != null || passwordError != null) {
            _uiState.value = currentState.copy(emailError = emailError, passwordError = passwordError)
            isValid = false
        }

        return isValid
    }
}
