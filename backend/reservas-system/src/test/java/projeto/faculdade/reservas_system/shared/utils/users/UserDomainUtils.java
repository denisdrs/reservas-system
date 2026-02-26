package projeto.faculdade.reservas_system.shared.utils.users;

import projeto.faculdade.reservas_system.application.user.domain.Address;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.application.user.usecase.contract.RegisterUserInput;

public class UserDomainUtils {

    public static RegisterUserInput createValidRegisterUserInput() {
        RegisterUserInput.AddressInput address = new RegisterUserInput.AddressInput("Rua Exemplo", 123L, "Cidade Exemplo", "Estado Exemplo");
        return new RegisterUserInput(
                "Joao",
                "joao.test@example.com",
                "password123",
                address
        );
    }

    public static User createValidUser() {
        Address address1 = new Address("Rua Exemplo", 123L, "Cidade Exemplo", "Estado Exemplo");
        return User.builder()
                .name("Joao").email("joao.test@example.com").password("password123")
                .address(address1).build();
    }
}
