package projeto.faculdade.reservas_system.application.order.usecase.contract;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderOutput(String orderId, List<ProductOutput> products,
                          BigDecimal total, LocalDateTime createdAt) {

    public record ProductOutput(String name, BigDecimal value) {

    }
}
