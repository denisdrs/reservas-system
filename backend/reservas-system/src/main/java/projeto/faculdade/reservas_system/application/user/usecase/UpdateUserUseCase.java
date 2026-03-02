package projeto.faculdade.reservas_system.application.user.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.application.user.exception.UserNotFoundException;
import projeto.faculdade.reservas_system.application.user.port.UserRepository;
import projeto.faculdade.reservas_system.application.user.usecase.contract.UpdateUserInput;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserUseCase {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void execute(String id, UpdateUserInput input) {
        User user = userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);
        log.info("Updating user with id: {}", id);
        userRepository.save(updateUser(user,input));
    }

    private User updateUser(User user, UpdateUserInput input) {
        user.setName(input.name());
        user.setPassword(passwordEncoder.encode(input.password()));
        user.getAddress().setStreet(input.address().street());
        user.getAddress().setNumber(input.address().number());
        user.getAddress().setCity(input.address().city());
        user.getAddress().setState(input.address().state());
        return user;
    }
}
