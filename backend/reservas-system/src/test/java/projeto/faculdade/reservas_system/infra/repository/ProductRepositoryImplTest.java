package projeto.faculdade.reservas_system.infra.repository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import projeto.faculdade.reservas_system.application.product.domain.Product;
import projeto.faculdade.reservas_system.infra.repository.dao.ProductDAO;
import projeto.faculdade.reservas_system.shared.utils.products.ProductDomainUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
public class ProductRepositoryImplTest {

    @Mock
    private ProductDAO productDAO;

    @InjectMocks
    private ProductRepositoryImpl productRepository;

    @Test
    void save_whenValidProduct_shouldCallDaoSave() {
        Product product = ProductDomainUtils.createValidProduct();
        productRepository.save(product);
        verify(productDAO).save(product);
    }

    @Test
    void findAll_shouldCallDaoFindAll() {
        List<Product> products = List.of(ProductDomainUtils.createValidProduct());
        when(productDAO.findAll()).thenReturn(products);

        List<Product> result = productRepository.findAll();

        assertEquals(products, result);
        verify(productDAO).findAll();
    }

    @Test
    void delete_whenProductExists_shouldCallDaoDelete() {
        Product product = ProductDomainUtils.createValidProduct();
        productRepository.delete(product);
        verify(productDAO).delete(product);
    }

    @Test
    void findById_whenProductExists_shouldReturnProduct() {
        String productId = "123";
        Product product = ProductDomainUtils.createValidProduct();
        when(productDAO.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> result = productRepository.findById(productId);

        assertTrue(result.isPresent());
        assertEquals(product, result.get());
        verify(productDAO).findById(productId);
    }

    @Test
    void findById_whenProductDoesNotExist_shouldReturnEmpty() {
        String productId = "123";
        when(productDAO.findById(productId)).thenReturn(Optional.empty());

        Optional<Product> result = productRepository.findById(productId);

        assertTrue(result.isEmpty());
        verify(productDAO).findById(productId);
    }
}
