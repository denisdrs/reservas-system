package projeto.faculdade.reservas_system.presentation.rest.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.faculdade.reservas_system.infra.security.JwtService;
import projeto.faculdade.reservas_system.presentation.rest.auth.contract.LoginRequest;
import projeto.faculdade.reservas_system.presentation.rest.auth.contract.LoginResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager manager;

    private final JwtService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication auth = manager.authenticate(authToken);
        UserDetails user  = (UserDetails) auth.getPrincipal();
        log.info("User with email: {} authenticated", user.getUsername());
        String token = tokenService.generateToken(request.email());
        return ResponseEntity.ok(new LoginResponse("Bearer " + token));
    }
}