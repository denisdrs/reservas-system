package projeto.faculdade.reservas_system.infra.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import projeto.faculdade.reservas_system.application.product.domain.Product;
import projeto.faculdade.reservas_system.application.product.port.ProductRepository;
import projeto.faculdade.reservas_system.infra.repository.dao.ProductDAO;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductDAO productDAO;

    @Override
    public void save(Product product) {
        log.info("Saving new product with name: {}", product.getName());
        productDAO.save(product);
    }

    @Override
    public List<Product> findAll() {
        log.info("Finding all products");
        return productDAO.findAll();
    }

    @Override
    public void delete(Product product) {
        log.info("Deleting product with id: {}", product.getId());
        productDAO.delete(product);
    }

    @Override
    public Optional<Product> findById(String id) {
        log.info("Finding product with id: {}", id);
        return productDAO.findById(id);
    }
}
