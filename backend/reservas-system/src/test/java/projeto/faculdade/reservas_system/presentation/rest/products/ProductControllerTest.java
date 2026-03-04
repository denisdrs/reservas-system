package projeto.faculdade.reservas_system.presentation.rest.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import projeto.faculdade.reservas_system.application.product.usecase.RegisterProductUseCase;
import projeto.faculdade.reservas_system.application.product.usecase.contract.RegisterProductInput;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.presentation.rest.products.contract.RegisterProductRequest;
import projeto.faculdade.reservas_system.presentation.rest.products.mapper.ProductControllerMapper;
import projeto.faculdade.reservas_system.shared.utils.products.ProductControllerUtils;
import projeto.faculdade.reservas_system.shared.utils.products.ProductDomainUtils;
import projeto.faculdade.reservas_system.shared.utils.users.UserDomainUtils;
import tools.jackson.databind.ObjectMapper;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductControllerTest {

    @MockitoBean
    private RegisterProductUseCase registerProductUseCase;

    @MockitoBean
    private ProductControllerMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private RequestPostProcessor mockUser() {
        User user = UserDomainUtils.createValidUser();
        user.getRole().setRoleName("ADMIN");
        user.setId("123");
        return authentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
    }

    @Test
    void register_whenValidRequest_shouldReturn201() throws Exception {
        when(mapper.toRegisterProductInput(any(RegisterProductRequest.class))).thenReturn(ProductDomainUtils.createValidRegisterProductInput());
        doNothing().when(registerProductUseCase).execute(any(RegisterProductInput.class), any(User.class));

        mockMvc.perform(post("/api/products")
                        .with(mockUser())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ProductControllerUtils.createValidRegisterProductRequest())))
                .andExpect(status().isCreated());
    }
}
