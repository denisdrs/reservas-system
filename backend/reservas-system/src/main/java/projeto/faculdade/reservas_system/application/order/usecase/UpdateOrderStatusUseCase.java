package projeto.faculdade.reservas_system.application.order.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import projeto.faculdade.reservas_system.application.order.domain.Order;
import projeto.faculdade.reservas_system.application.order.domain.OrderStatus;
import projeto.faculdade.reservas_system.application.order.exception.OrderNotFoundException;
import projeto.faculdade.reservas_system.application.order.port.OrderRepository;
import projeto.faculdade.reservas_system.application.shared.contract.DefaultMessage;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateOrderStatusUseCase {
    private final OrderRepository orderRepository;

    public DefaultMessage<Order> execute(String orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.setStatus(OrderStatus.valueOf(status));
        log.info("Order: {} atualizada para o status: {}",orderId, status);
        return DefaultMessage.<Order>builder()
                .data(order)
                .message("Order update successful")
                .build();
    }
}
