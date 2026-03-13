package projeto.faculdade.reservas_system.application.order.usecase.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import projeto.faculdade.reservas_system.application.order.domain.Order;
import projeto.faculdade.reservas_system.application.order.usecase.contract.OrderOutput;

@Mapper(componentModel = "spring")
public interface OrderUseCaseMapper {

    @Mapping(target = "orderId", source = "id")
    OrderOutput toOrderOutput(Order order);
}
