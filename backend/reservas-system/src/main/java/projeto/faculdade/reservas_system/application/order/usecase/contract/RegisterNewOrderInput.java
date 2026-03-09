package projeto.faculdade.reservas_system.application.order.usecase.contract;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record RegisterNewOrderInput(List<ProductInput> products) {

    public record ProductInput(String id, BigDecimal quantity) {}
}
