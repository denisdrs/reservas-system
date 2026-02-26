package projeto.faculdade.reservas_system.application.user.usecase;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.security.crypto.password.PasswordEncoder;
import projeto.faculdade.reservas_system.application.user.domain.Address;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.application.user.exception.EmailAlreadyUsedException;
import projeto.faculdade.reservas_system.application.user.port.UserRepository;
import projeto.faculdade.reservas_system.application.user.usecase.contract.RegisterUserInput;
import projeto.faculdade.reservas_system.application.user.usecase.mapper.RegisterUserUseCaseMapper;
import projeto.faculdade.reservas_system.shared.utils.users.UserDomainUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@MockitoSettings
public class RegisterUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RegisterUserUseCaseMapper mapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegisterUserUseCase registerUserUseCase;

    @Test
    void execute_whenValidInput_shouldSetPasswordAndCallUserRepository() {

        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.empty());
        when(mapper.toUser(any(RegisterUserInput.class))).thenReturn(UserDomainUtils.createValidUser());
        when(passwordEncoder.encode(anyString())).thenReturn("password123");
        doNothing().when(userRepository).save(any(User.class));

        registerUserUseCase.execute(UserDomainUtils.createValidRegisterUserInput());
    }

    @Test
    void execute_whenValidInputButEmailAlreadyUsed_shouldThrowEmailAlreadyUsedException() {
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(UserDomainUtils.createValidUser()));

        EmailAlreadyUsedException exception = assertThrows(EmailAlreadyUsedException.class, () -> registerUserUseCase.execute(UserDomainUtils.createValidRegisterUserInput()));

        assertEquals("Email already registered", exception.getMessage());
    }
}
