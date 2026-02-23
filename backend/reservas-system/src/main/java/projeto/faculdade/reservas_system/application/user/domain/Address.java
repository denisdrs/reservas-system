package projeto.faculdade.reservas_system.application.user.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    
    private String street;
    
    private Long number;
    
    private String city;
    
    private String state;
}
