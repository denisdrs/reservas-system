package projeto.faculdade.reservas_system.presentation.rest.products.mapper;

import org.mapstruct.Mapper;
import projeto.faculdade.reservas_system.application.product.usecase.contract.RegisterProductInput;
import projeto.faculdade.reservas_system.presentation.rest.products.contract.RegisterProductRequest;

@Mapper(componentModel = "spring")
public interface ProductControllerMapper {

    RegisterProductInput toRegisterProductInput(RegisterProductRequest request);
}
