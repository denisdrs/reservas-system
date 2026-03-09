package projeto.faculdade.reservas_system.application.product.usecase;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import projeto.faculdade.reservas_system.application.product.domain.Product;
import projeto.faculdade.reservas_system.application.product.port.ProductRepository;
import projeto.faculdade.reservas_system.application.product.usecase.contract.RegisterProductInput;
import projeto.faculdade.reservas_system.application.product.usecase.mapper.ProductUseCaseMapper;
import projeto.faculdade.reservas_system.shared.utils.products.ProductDomainUtils;
import projeto.faculdade.reservas_system.shared.utils.users.UserDomainUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@MockitoSettings
public class RegisterProductUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductUseCaseMapper mapper;

    @InjectMocks
    private RegisterProductUseCase registerProductUseCase;

    @Test
    void execute_shouldSaveProduct() {
        RegisterProductInput input = ProductDomainUtils.createValidRegisterProductInput();
        Product product = ProductDomainUtils.createValidProduct();

        when(mapper.toProduct(any(RegisterProductInput.class))).thenReturn(product);

        registerProductUseCase.execute(input, UserDomainUtils.createValidUser());

        verify(productRepository).save(product);
    }
}
