package projeto.faculdade.reservas_system.application.user.usecase.mapper;

import org.mapstruct.Mapper;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.application.user.usecase.contract.RegisterUserInput;

@Mapper(componentModel = "spring")
public interface RegisterUserUseCaseMapper {

    User toUser(RegisterUserInput input);
}
