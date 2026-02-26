package projeto.faculdade.reservas_system.presentation.rest.users.mapper;

import org.mapstruct.Mapper;
import projeto.faculdade.reservas_system.application.user.usecase.contract.RegisterUserInput;
import projeto.faculdade.reservas_system.presentation.rest.users.contract.RegisterUserRequest;

@Mapper(componentModel = "spring")
public interface UserControllerMapper {

    RegisterUserInput toRegisterUserInput(RegisterUserRequest request);
}
