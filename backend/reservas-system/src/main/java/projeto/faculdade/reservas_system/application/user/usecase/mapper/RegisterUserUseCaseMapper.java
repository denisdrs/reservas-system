package projeto.faculdade.reservas_system.application.user.usecase.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.application.user.usecase.contract.RegisterUserInput;

@Mapper(componentModel = "spring")
public interface RegisterUserUseCaseMapper {

    @Mapping(target = "id", ignore = true)
    User toUser(RegisterUserInput input);
}
