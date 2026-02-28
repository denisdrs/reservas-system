package projeto.faculdade.reservas_system.infra.repository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.infra.repository.dao.UserDAO;
import projeto.faculdade.reservas_system.shared.utils.users.UserDomainUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@MockitoSettings
public class UserRepositoryImplTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Test
    void save_whenValidUser_shouldCallDaoSave() {
        User user = UserDomainUtils.createValidUser();
        userRepository.save(user);
        verify(userDAO).save(user);
    }

    @Test
    void findUserById_whenUserExistsWithId_shouldCallDaoFindById() {
        String userId = "123";
        User user = UserDomainUtils.createValidUser();
        when(userDAO.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> result = userRepository.findUserById(userId);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userDAO).findById(userId);
    }

    @Test
    void findUserById_whenUserNotExistsWithId_shouldReturnEmptyOptional() {
        String userId = "123";
        when(userDAO.findById(userId)).thenReturn(Optional.empty());

        Optional<User> result = userRepository.findUserById(userId);

        assertTrue(result.isEmpty());
        verify(userDAO).findById(userId);
    }

    @Test
    void findUserByEmail_whenUserExistsWithEmail_shouldCallDaoFindByEmail() {
        String email = "test@test.com";
        User user = UserDomainUtils.createValidUser();
        when(userDAO.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = userRepository.findUserByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userDAO).findByEmail(email);
    }

    @Test
    void findUserByEmail_whenUserNotExistsWithEmail_shouldReturnEmptyOptional() {
        String email = "test@test.com";
        when(userDAO.findByEmail(email)).thenReturn(Optional.empty());

        Optional<User> result = userRepository.findUserByEmail(email);

        assertTrue(result.isEmpty());
        verify(userDAO).findByEmail(email);
    }

    @Test
    void deleteUser_whenUserExists_shouldCallDaoDelete() {
        User user = UserDomainUtils.createValidUser();
        userRepository.deleteUser(user);
        verify(userDAO).delete(user);
    }
}
