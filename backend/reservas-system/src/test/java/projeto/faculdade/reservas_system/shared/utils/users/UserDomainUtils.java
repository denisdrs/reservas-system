package projeto.faculdade.reservas_system.shared.utils.users;

import projeto.faculdade.reservas_system.application.user.domain.Address;
import projeto.faculdade.reservas_system.application.user.domain.Role;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.application.user.usecase.contract.RegisterUserInput;
import projeto.faculdade.reservas_system.application.user.usecase.contract.UpdateUserInput;

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

    public static UpdateUserInput createValidUpdateUserInput() {
        UpdateUserInput.AddressInput address = new UpdateUserInput.AddressInput("Rua Nova", 321L, "Cidade Nova", "Estado Novo");
        return new UpdateUserInput(
                "Joao Novo",
                "newPassword123",
                address
        );
    }

    public static User createValidUser() {
        Address address1 = new Address("Rua Exemplo", 123L, "Cidade Exemplo", "Estado Exemplo");
        return User.builder()
                .name("Joao").email("joao.test@example.com").password("password123").role(new Role("USER"))
                .address(address1).build();
    }
}
