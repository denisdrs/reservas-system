package projeto.faculdade.reservas_system.application.user.usecase.contract;

import lombok.Builder;
import lombok.With;

public record UpdateUserInput(String name, String password, AddressInput address) {

    @Builder
    @With
    public record AddressInput(String street, Long number, String city, String state) {

    }
}
