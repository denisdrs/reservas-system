package projeto.faculdade.reservas_system.application.user.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Role implements GrantedAuthority {

    private String roleName;

    @Override
    public @Nullable String getAuthority() {
        return this.roleName;
    }
}
