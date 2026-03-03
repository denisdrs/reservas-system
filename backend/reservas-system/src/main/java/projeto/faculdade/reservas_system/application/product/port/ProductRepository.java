package projeto.faculdade.reservas_system.application.product.port;

import projeto.faculdade.reservas_system.application.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(Product product);

    List<Product> findAll();

    void delete(Product product);

    Optional<Product> findById(String id);
}
