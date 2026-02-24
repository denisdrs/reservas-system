package projeto.faculdade.reservas_system.application.user.port;

import projeto.faculdade.reservas_system.application.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findUserById(String id);

    Optional<User> findUserByEmail(String email);

    void deleteUser(User user);
}
