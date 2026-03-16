package projeto.faculdade.reservas_system.application.order.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projeto.faculdade.reservas_system.application.order.domain.Order;
import projeto.faculdade.reservas_system.application.order.domain.OrderStatus;
import projeto.faculdade.reservas_system.application.order.exception.StockException;
import projeto.faculdade.reservas_system.application.order.port.OrderRepository;
import projeto.faculdade.reservas_system.application.order.usecase.contract.RegisterNewOrderInput;
import projeto.faculdade.reservas_system.application.product.domain.Product;
import projeto.faculdade.reservas_system.application.product.exception.ProductNotFoundException;
import projeto.faculdade.reservas_system.application.product.port.ProductRepository;
import projeto.faculdade.reservas_system.application.shared.contract.DefaultMessage;
import projeto.faculdade.reservas_system.application.user.domain.User;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterNewOrderUseCase {
    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public DefaultMessage<Order> execute(RegisterNewOrderInput input, User user) {
        List<Product> productList = input.products().stream()
                .map(productInput -> productRepository.findById(productInput.id()))
                .map(optionalProduct -> optionalProduct.orElseThrow(ProductNotFoundException::new))
                .toList();
        Order order = Order.builder()
                .products(productList)
                .user(user)
                .total(getTotal(productList, input))
                .status(OrderStatus.PENDENTE)
                .build();
        orderRepository.save(order);
        removeProductsFromStock(productList, input);
        return DefaultMessage.<Order>builder()
                .message("Order successful created")
                .data(order)
                .build();
    }

    private BigDecimal getTotal(List<Product> productList, RegisterNewOrderInput input) {
        BigDecimal total = BigDecimal.ZERO;
        return productList.stream().map(product -> {
            var productInputValue =  input.products().stream()
                    .filter(productInput -> productInput.id().equalsIgnoreCase(product.getId())).toList()
                    .stream().findFirst();
            if(Long.parseLong(productInputValue.get().quantity().toString()) > product.getQuantity()) {
                throw new StockException();
            }
            return total.add(productInputValue.get().quantity().multiply(product.getValue()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void removeProductsFromStock(List<Product> productList,RegisterNewOrderInput input ) {
        productList.stream().forEach(product -> {
            for (RegisterNewOrderInput.ProductInput productInput : input.products()) {
                if(productInput.id().equalsIgnoreCase(product.getId())) {
                    product.setQuantity(product.getQuantity() - Long.parseLong(productInput.quantity().toString()));
                    productRepository.save(product);
                }
            }
        });
    }
}
