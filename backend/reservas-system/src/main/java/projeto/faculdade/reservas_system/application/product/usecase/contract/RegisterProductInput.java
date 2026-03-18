package projeto.faculdade.reservas_system.application.product.usecase.contract;

import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;

@Builder
@With
public record RegisterProductInput(String name, BigDecimal value, Long quantity, String url) {
}
