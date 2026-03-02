package projeto.faculdade.reservas_system.presentation.rest.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.application.user.exception.EmailAlreadyUsedException;
import projeto.faculdade.reservas_system.application.user.exception.UserNotFoundException;
import projeto.faculdade.reservas_system.application.user.usecase.RegisterUserUseCase;
import projeto.faculdade.reservas_system.application.user.usecase.UpdateUserUseCase;
import projeto.faculdade.reservas_system.application.user.usecase.contract.RegisterUserInput;
import projeto.faculdade.reservas_system.application.user.usecase.contract.UpdateUserInput;
import projeto.faculdade.reservas_system.presentation.rest.users.contract.RegisterUserRequest;
import projeto.faculdade.reservas_system.presentation.rest.users.contract.UpdateUserRequest;
import projeto.faculdade.reservas_system.presentation.rest.users.mapper.UserControllerMapper;
import projeto.faculdade.reservas_system.shared.utils.users.UserControllerUtils;
import projeto.faculdade.reservas_system.shared.utils.users.UserDomainUtils;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @MockitoBean
    private RegisterUserUseCase registerUserUseCase;

    @MockitoBean
    private UpdateUserUseCase updateUserUseCase;

    @MockitoBean
    private UserControllerMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private RequestPostProcessor mockUser() {
        User user = UserDomainUtils.createValidUser();
        user.setId("123");
        return authentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
    }

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

    @Test
    void update_whenValidRequest_shouldReturn200() throws Exception {
        when(mapper.toUpdateUserInput(any(UpdateUserRequest.class))).thenReturn(UserDomainUtils.createValidUpdateUserInput());
        doNothing().when(updateUserUseCase).execute(anyString(), any(UpdateUserInput.class));

        mockMvc.perform(put("/api/users")
                        .with(mockUser())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserControllerUtils.createValidUpdateUserRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User updated successfully"));
    }

    @Test
    void update_whenUserNotFound_shouldReturn404() throws Exception {
        when(mapper.toUpdateUserInput(any(UpdateUserRequest.class))).thenReturn(UserDomainUtils.createValidUpdateUserInput());
        doThrow(new UserNotFoundException()).when(updateUserUseCase).execute(anyString(), any(UpdateUserInput.class));

        mockMvc.perform(put("/api/users")
                        .with(mockUser())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserControllerUtils.createValidUpdateUserRequest())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("User not found"));
    }
}
