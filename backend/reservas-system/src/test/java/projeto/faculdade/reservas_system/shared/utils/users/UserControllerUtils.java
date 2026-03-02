package projeto.faculdade.reservas_system.shared.utils.users;

import projeto.faculdade.reservas_system.presentation.rest.users.contract.RegisterUserRequest;
import projeto.faculdade.reservas_system.presentation.rest.users.contract.UpdateUserRequest;

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

    public static UpdateUserRequest createValidUpdateUserRequest() {
        UpdateUserRequest.AddressRequest address = new UpdateUserRequest.AddressRequest(
                "New Street",
                321L,
                "New City",
                "New State"
        );
        return new UpdateUserRequest(
                "Joao New",
                "newPassword123",
                address
        );
    }
}
