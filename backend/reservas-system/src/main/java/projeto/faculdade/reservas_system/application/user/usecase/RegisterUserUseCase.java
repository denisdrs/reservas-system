package projeto.faculdade.reservas_system.application.user.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.application.user.exception.EmailAlreadyUsedException;
import projeto.faculdade.reservas_system.application.user.port.UserRepository;
import projeto.faculdade.reservas_system.application.user.usecase.contract.RegisterUserInput;
import projeto.faculdade.reservas_system.application.user.usecase.mapper.RegisterUserUseCaseMapper;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserRepository userRepository;

    private final RegisterUserUseCaseMapper mapper;

    private final PasswordEncoder passwordEncoder;

    public void execute(RegisterUserInput input) {
        userRepository.findUserByEmail(input.email())
                .ifPresent(user -> {
                    throw new EmailAlreadyUsedException();
                });
        User user = mapper.toUser(input);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
