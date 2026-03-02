package projeto.faculdade.reservas_system.application.user.usecase;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.security.crypto.password.PasswordEncoder;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.application.user.exception.UserNotFoundException;
import projeto.faculdade.reservas_system.application.user.port.UserRepository;
import projeto.faculdade.reservas_system.shared.utils.users.UserDomainUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@MockitoSettings
public class UpdateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UpdateUserUseCase updateUserUseCase;

    @Test
    void execute_whenValidInput_shouldUpdateUser() {
        when(userRepository.findUserById(anyString())).thenReturn(Optional.of(UserDomainUtils.createValidUser()));
        when(passwordEncoder.encode(anyString())).thenReturn("newPassword123");
        doNothing().when(userRepository).save(any(User.class));

        updateUserUseCase.execute("1", UserDomainUtils.createValidUpdateUserInput());

        verify(userRepository).findUserById("1");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void execute_whenUserNotFound_shouldThrowUserNotFoundException() {
        when(userRepository.findUserById(anyString())).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> updateUserUseCase.execute("1", UserDomainUtils.createValidUpdateUserInput()));

        assertEquals("User not found", exception.getMessage());
        verify(userRepository).findUserById("1");
        verify(userRepository, never()).save(any(User.class));
    }
}
