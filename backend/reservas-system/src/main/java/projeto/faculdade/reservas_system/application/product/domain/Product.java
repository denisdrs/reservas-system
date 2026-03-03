package projeto.faculdade.reservas_system.application.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto.faculdade.reservas_system.application.user.domain.User;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private BigDecimal value;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
