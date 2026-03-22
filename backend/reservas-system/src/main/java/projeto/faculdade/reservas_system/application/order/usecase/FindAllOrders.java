package projeto.faculdade.reservas_system.application.order.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.faculdade.reservas_system.application.order.port.OrderRepository;
import projeto.faculdade.reservas_system.application.order.usecase.contract.OrderOutput;
import projeto.faculdade.reservas_system.application.order.usecase.mapper.OrderUseCaseMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllOrders {

    private final OrderRepository orderRepository;

    private final OrderUseCaseMapper mapper;

    public List<OrderOutput> execute() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::toOrderOutput).toList();
    }
}
