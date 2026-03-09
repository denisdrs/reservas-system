package projeto.faculdade.reservas_system.shared.utils.orders;

import projeto.faculdade.reservas_system.application.order.domain.Order;
import projeto.faculdade.reservas_system.shared.utils.users.UserDomainUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDomainUtils {

    public static Order createValidOrder() {
        return Order.builder()
                .id("123")
                .user(UserDomainUtils.createValidUser())
                .createdAt(LocalDateTime.now())
                .total(new BigDecimal("100.00"))
                .build();
    }
}
