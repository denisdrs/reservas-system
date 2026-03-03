package projeto.faculdade.reservas_system.infra.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.faculdade.reservas_system.application.product.domain.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, String> {
}
