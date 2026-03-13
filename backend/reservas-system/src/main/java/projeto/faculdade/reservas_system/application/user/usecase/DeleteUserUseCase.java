package projeto.faculdade.reservas_system.application.user.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.faculdade.reservas_system.application.shared.contract.DefaultMessage;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.application.user.port.UserRepository;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserRepository userRepository;

    public DefaultMessage<User> execute(User user) {
        userRepository.deleteUser(user);
        return DefaultMessage.<User>builder()
                .message("Usuario deletado com sucesso")
                .data(user.withPassword(""))
                .build();
    }
}
