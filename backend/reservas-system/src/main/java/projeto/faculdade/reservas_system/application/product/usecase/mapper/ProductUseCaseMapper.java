package projeto.faculdade.reservas_system.application.product.usecase.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import projeto.faculdade.reservas_system.application.product.domain.Product;
import projeto.faculdade.reservas_system.application.product.usecase.contract.ProductOutput;
import projeto.faculdade.reservas_system.application.product.usecase.contract.RegisterProductInput;

@Mapper(componentModel = "spring")
public interface ProductUseCaseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Product toProduct(RegisterProductInput input);

    @Mapping(source = "url", target = "url")
    ProductOutput toProductOutput(Product product);
}
