package projeto.faculdade.reservas_system.infra.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.application.user.port.UserRepository;
import projeto.faculdade.reservas_system.infra.repository.dao.UserDAO;

import java.util.Optional;

@Component
@RequiredArgsConstructor @Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final UserDAO dao;

    @Override
    public void save(User user) {
        log.info("Saving new user with email: {}", user.getEmail());
        dao.save(user);
    }

    @Override
    public Optional<User> findUserById(String id) {
        log.info("Finding user with id: {}", id);
        return dao.findById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        log.info("Finding user with email: {}", email);
        return dao.findByEmail(email);
    }

    @Override
    public void deleteUser(User user) {
        log.info("Deleting user with id: {}", user.getId());
        dao.delete(user);
    }
}
