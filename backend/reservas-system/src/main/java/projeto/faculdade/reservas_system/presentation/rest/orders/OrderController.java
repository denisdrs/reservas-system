package projeto.faculdade.reservas_system.presentation.rest.orders;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import projeto.faculdade.reservas_system.application.order.domain.Order;
import projeto.faculdade.reservas_system.application.order.usecase.FindAllOrdersByUser;
import projeto.faculdade.reservas_system.application.order.usecase.RegisterNewOrderUseCase;
import projeto.faculdade.reservas_system.application.order.usecase.UpdateOrderStatusUseCase;
import projeto.faculdade.reservas_system.application.order.usecase.contract.OrderOutput;
import projeto.faculdade.reservas_system.application.shared.contract.DefaultMessage;
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

    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    private final OrderControllerMapper mapper;

    @PostMapping
    public ResponseEntity<DefaultMessage<Order>> register(@Valid @RequestBody RegisterNewOrderRequest request, @AuthenticationPrincipal User user) {
        return ResponseEntity.status(201)
                .body(registerNewOrderUseCase.execute(mapper.toRegisterNewOrderInput(request), user));
    }

    @GetMapping
    public ResponseEntity<List<OrderOutput>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(findAllOrdersByUser.execute(user));
    }

    @PutMapping("/{orderId}/{status}")
    public ResponseEntity<DefaultMessage<Order>> updateStatus(@PathVariable String orderId, @PathVariable String status) {
        return ResponseEntity.ok(updateOrderStatusUseCase.execute(orderId,status));
    }
}
