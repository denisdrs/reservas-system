package projeto.faculdade.reservas_system.application.product.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.faculdade.reservas_system.application.product.port.ProductRepository;
import projeto.faculdade.reservas_system.application.product.usecase.contract.ProductOutput;
import projeto.faculdade.reservas_system.application.product.usecase.mapper.ProductUseCaseMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllProductsUseCase {
    private final ProductRepository productRepository;

    private final ProductUseCaseMapper mapper;

    public List<ProductOutput> execute() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductOutput)
                .toList();
    }
}
