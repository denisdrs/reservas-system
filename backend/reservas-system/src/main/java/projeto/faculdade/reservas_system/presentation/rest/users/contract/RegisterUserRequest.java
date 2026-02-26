package projeto.faculdade.reservas_system.presentation.rest.users.contract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

@Builder
@With
public record RegisterUserRequest(
        @NotBlank(message = "Name não pode ser vazio") String name,
        @NotBlank(message = "Email não pode ser vazio")  String email,
        @NotBlank(message = "Password não pode ser vazio") String password,
        @NotNull(message = "Address não pode ser null") AddressRequest address) {

    public record AddressRequest(
            @NotBlank(message = "Street não pode ser vazio") String street,
            @NotNull(message = "Number não pode ser null") Long number,
            @NotBlank(message = "City não pode ser vazio") String city,
            @NotBlank(message = "State não pode ser vazio") String state) {
    }
}
