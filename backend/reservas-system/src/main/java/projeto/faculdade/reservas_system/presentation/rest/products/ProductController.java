package projeto.faculdade.reservas_system.presentation.rest.products;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.faculdade.reservas_system.application.product.usecase.RegisterProductUseCase;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.presentation.rest.products.contract.RegisterProductRequest;
import projeto.faculdade.reservas_system.presentation.rest.products.mapper.ProductControllerMapper;

import java.net.URI;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final RegisterProductUseCase registerProductUseCase;

    private final ProductControllerMapper mapper;

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterProductRequest request, @AuthenticationPrincipal User user) {
        registerProductUseCase.execute(mapper.toRegisterProductInput(request), user);
        return ResponseEntity.created(URI.create("/api/products/")).build();
    }
}
