package projeto.faculdade.reservas_system.presentation.rest.users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.faculdade.reservas_system.application.user.usecase.RegisterUserUseCase;
import projeto.faculdade.reservas_system.presentation.rest.users.contract.RegisterUserRequest;
import projeto.faculdade.reservas_system.presentation.rest.users.mapper.UserControllerMapper;

import java.net.URI;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;

    private final UserControllerMapper mapper;

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterUserRequest request) {
        registerUserUseCase.execute(mapper.toRegisterUserInput(request));
        return ResponseEntity.created(URI.create("/api/users/")).build();
    }
}
