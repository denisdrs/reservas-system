package projeto.faculdade.reservas_system.presentation.rest.orders.contract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record RegisterNewOrderRequest(@NotEmpty(message ="Products não pode ser vazio" ) List<ProductRequest> products) {

    public record ProductRequest(@NotBlank(message = "Id não pode ser vazio") String id,
                                 @NotNull(message = "Quantity não pode ser null")
                                 @NotBlank(message = "Quantity não pode ser vazio")
                                 @Positive BigDecimal quantity) {}
}
