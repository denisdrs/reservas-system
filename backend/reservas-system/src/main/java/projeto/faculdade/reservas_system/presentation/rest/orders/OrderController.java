package projeto.faculdade.reservas_system.presentation.rest.orders;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.faculdade.reservas_system.application.order.usecase.RegisterNewOrderUseCase;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.presentation.rest.orders.contract.RegisterNewOrderRequest;
import projeto.faculdade.reservas_system.presentation.rest.orders.mapper.OrderControllerMapper;

@RequestMapping("/api/orders")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final RegisterNewOrderUseCase registerNewOrderUseCase;

    private final OrderControllerMapper mapper;

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterNewOrderRequest request, @AuthenticationPrincipal User user) {
        registerNewOrderUseCase.execute(
                mapper.toRegisterNewOrderInput(request), user);
        return ResponseEntity.ok().build();
    }
}
