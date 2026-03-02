package projeto.faculdade.reservas_system.presentation.rest.users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.application.user.usecase.RegisterUserUseCase;
import projeto.faculdade.reservas_system.application.user.usecase.UpdateUserUseCase;
import projeto.faculdade.reservas_system.presentation.rest.users.contract.RegisterUserRequest;
import projeto.faculdade.reservas_system.presentation.rest.users.contract.UpdateUserRequest;
import projeto.faculdade.reservas_system.presentation.rest.users.contract.UpdateUserResponse;
import projeto.faculdade.reservas_system.presentation.rest.users.mapper.UserControllerMapper;

import java.net.URI;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;

    private final UpdateUserUseCase updateUserUseCase;

    private final UserControllerMapper mapper;

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterUserRequest request) {
        registerUserUseCase.execute(mapper.toRegisterUserInput(request));
        return ResponseEntity.created(URI.create("/api/users/")).build();
    }

    @PutMapping
    public ResponseEntity<UpdateUserResponse> update(@Valid @RequestBody UpdateUserRequest request, @AuthenticationPrincipal User user) {
        updateUserUseCase.execute(user.getId(), mapper.toUpdateUserInput(request));
        UpdateUserResponse response = UpdateUserResponse.builder().message("User updated successfully").build();
        return ResponseEntity.ok().body(response);
    }
}
