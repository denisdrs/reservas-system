package projeto.faculdade.reservas_system.application.product.usecase.contract;

import java.math.BigDecimal;

public record ProductOutput(String id,String name,BigDecimal value, Long quantity) {
}
