package projeto.faculdade.reservas_system.application.user.usecase.contract;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record RegisterUserInput(String name, String email, String password, AddressInput address) {

    @Builder
    @With
    public record AddressInput(String street, Long number, String city, String state) {

    }
}
