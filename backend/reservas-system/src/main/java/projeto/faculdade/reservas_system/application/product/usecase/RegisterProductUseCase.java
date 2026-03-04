package projeto.faculdade.reservas_system.application.product.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.faculdade.reservas_system.application.product.domain.Product;
import projeto.faculdade.reservas_system.application.product.port.ProductRepository;
import projeto.faculdade.reservas_system.application.product.usecase.contract.RegisterProductInput;
import projeto.faculdade.reservas_system.application.product.usecase.mapper.RegisterProductMapper;
import projeto.faculdade.reservas_system.application.user.domain.User;

@Service
@RequiredArgsConstructor
public class RegisterProductUseCase {

    private final ProductRepository productRepository;

    private final RegisterProductMapper mapper;

    public void execute(RegisterProductInput input, User user) {
        Product product = mapper.toProduct(input);
        product.setUser(user);
        productRepository.save(product);
    }
}
