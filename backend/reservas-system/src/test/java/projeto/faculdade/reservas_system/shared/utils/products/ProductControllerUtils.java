package projeto.faculdade.reservas_system.shared.utils.products;

import projeto.faculdade.reservas_system.presentation.rest.products.contract.RegisterProductRequest;

import java.math.BigDecimal;

public class ProductControllerUtils {

    public static RegisterProductRequest createValidRegisterProductRequest() {
        return RegisterProductRequest.builder()
                .name("Test Product")
                .value(BigDecimal.valueOf(10.00))
                .quantity(10L)
                .build();
    }
}
