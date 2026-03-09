package projeto.faculdade.reservas_system.presentation.rest.orders;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import projeto.faculdade.reservas_system.application.order.usecase.FindAllOrdersByUser;
import projeto.faculdade.reservas_system.application.order.usecase.RegisterNewOrderUseCase;
import projeto.faculdade.reservas_system.application.order.usecase.contract.OrderOutput;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.presentation.rest.orders.contract.RegisterNewOrderRequest;
import projeto.faculdade.reservas_system.presentation.rest.orders.mapper.OrderControllerMapper;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/orders")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final RegisterNewOrderUseCase registerNewOrderUseCase;

    private final FindAllOrdersByUser findAllOrdersByUser;

    private final OrderControllerMapper mapper;

    @PostMapping
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterNewOrderRequest request, @AuthenticationPrincipal User user) {
        registerNewOrderUseCase.execute(
                mapper.toRegisterNewOrderInput(request), user);
        return ResponseEntity.created(URI.create("/api/orders")).build();
    }

    @GetMapping
    public ResponseEntity<List<OrderOutput>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(findAllOrdersByUser.execute(user));
    }
}
