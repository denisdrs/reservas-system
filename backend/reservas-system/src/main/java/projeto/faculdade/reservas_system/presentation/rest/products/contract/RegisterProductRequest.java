package projeto.faculdade.reservas_system.presentation.rest.products.contract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RegisterProductRequest(
        @NotBlank(message = "Name não pode ser vazio") String name,
        @NotNull(message = "Value não pode ser null") @Positive BigDecimal value,
        @NotNull(message = "Quantity não pode ser null") @Positive Long quantity,
        @NotBlank(message = "Url não pode ser vazio") String url) {
}
