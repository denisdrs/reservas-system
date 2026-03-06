package projeto.faculdade.reservas_system.application.order.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto.faculdade.reservas_system.application.product.domain.Product;
import projeto.faculdade.reservas_system.application.user.domain.User;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "order_products", // Nome da tabela de ligação
            joinColumns = @JoinColumn(name = "order_id"), // FK para Order
            inverseJoinColumns = @JoinColumn(name = "product_id") // FK para Product
    )
    private List<Product> products;

    private BigDecimal total;
}
