package projeto.faculdade.reservas_system.shared.utils.products;

import projeto.faculdade.reservas_system.application.product.domain.Product;
import projeto.faculdade.reservas_system.shared.utils.users.UserDomainUtils;

import java.math.BigDecimal;

public class ProductDomainUtils {

    public static Product createValidProduct() {
        return Product.builder()
                .id("123")
                .name("Test Product")
                .value(BigDecimal.valueOf(10.00))
                .quantity(10L)
                .user(UserDomainUtils.createValidUser())
                .build();
    }
}
