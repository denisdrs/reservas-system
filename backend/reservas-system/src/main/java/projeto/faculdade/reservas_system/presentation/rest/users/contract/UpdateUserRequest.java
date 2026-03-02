package projeto.faculdade.reservas_system.presentation.rest.users.contract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

public record UpdateUserRequest(
        @NotBlank(message = "Name não pode ser vazio") String name,
        @NotBlank(message = "Password não pode ser vazio") String password,
        AddressRequest address) {

    @Builder
    @With
    public record AddressRequest(@NotBlank(message = "Street não pode ser vazio") String street,
                                 @NotNull(message = "Number não pode ser null") Long number,
                                 @NotBlank(message = "City não pode ser vazio") String city,
                                 @NotBlank(message = "State não pode ser vazio") String state) {

    }
}
