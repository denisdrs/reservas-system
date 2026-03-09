package projeto.faculdade.reservas_system.presentation.rest.orders.mapper;

import org.mapstruct.Mapper;
import projeto.faculdade.reservas_system.application.order.usecase.contract.RegisterNewOrderInput;
import projeto.faculdade.reservas_system.presentation.rest.orders.contract.RegisterNewOrderRequest;

@Mapper(componentModel = "spring")
public interface OrderControllerMapper {

    RegisterNewOrderInput toRegisterNewOrderInput(RegisterNewOrderRequest request);
}
