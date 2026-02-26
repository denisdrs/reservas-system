package projeto.faculdade.reservas_system.presentation.rest.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import projeto.faculdade.reservas_system.application.user.exception.EmailAlreadyUsedException;
import projeto.faculdade.reservas_system.application.user.usecase.RegisterUserUseCase;
import projeto.faculdade.reservas_system.application.user.usecase.contract.RegisterUserInput;
import projeto.faculdade.reservas_system.presentation.rest.users.contract.RegisterUserRequest;
import projeto.faculdade.reservas_system.presentation.rest.users.mapper.UserControllerMapper;
import projeto.faculdade.reservas_system.shared.utils.users.UserControllerUtils;
import projeto.faculdade.reservas_system.shared.utils.users.UserDomainUtils;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @MockitoBean
    private RegisterUserUseCase registerUserUseCase;

    @MockitoBean
    private UserControllerMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void register_whenValidRequest_shouldReturn201() throws Exception {

        when(mapper.toRegisterUserInput(any(RegisterUserRequest.class))).thenReturn(UserDomainUtils.createValidRegisterUserInput());
        doNothing().when(registerUserUseCase).execute(any(RegisterUserInput.class));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserControllerUtils.createValidRegisterUserRequest())))
                .andExpect(status().isCreated());
    }

    @Test
    void register_whenValidRequestButEmailAlreadyUsed_shouldReturn400() throws Exception {
        when(mapper.toRegisterUserInput(any(RegisterUserRequest.class))).thenReturn(UserDomainUtils.createValidRegisterUserInput());
        doThrow(new EmailAlreadyUsedException()).when(registerUserUseCase).execute(any(RegisterUserInput.class));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserControllerUtils.createValidRegisterUserRequest())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Email already registered"));
    }
}
