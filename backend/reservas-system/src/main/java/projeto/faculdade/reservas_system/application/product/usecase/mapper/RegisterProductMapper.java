package projeto.faculdade.reservas_system.application.product.usecase.mapper;

import org.mapstruct.Mapper;
import projeto.faculdade.reservas_system.application.product.domain.Product;
import projeto.faculdade.reservas_system.application.product.usecase.contract.RegisterProductInput;

@Mapper(componentModel = "spring")
public interface RegisterProductMapper {

    Product toProduct(RegisterProductInput input);
}
