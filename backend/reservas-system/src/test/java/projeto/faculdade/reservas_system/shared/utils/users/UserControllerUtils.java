package projeto.faculdade.reservas_system.shared.utils.users;

import projeto.faculdade.reservas_system.presentation.rest.users.contract.RegisterUserRequest;

public class UserControllerUtils {

    public static RegisterUserRequest createValidRegisterUserRequest() {
        RegisterUserRequest.AddressRequest address1 = new RegisterUserRequest.AddressRequest(
                "Street",
                123L,
                "City",
                "State"
        );
        return new RegisterUserRequest(
                "Joao",
                "joao.test@example.com",
                "password123",
                address1
        );
    }
}
