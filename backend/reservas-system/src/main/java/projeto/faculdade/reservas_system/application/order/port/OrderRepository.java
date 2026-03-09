package projeto.faculdade.reservas_system.application.order.port;

import projeto.faculdade.reservas_system.application.order.domain.Order;
import projeto.faculdade.reservas_system.application.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(String id);

    void deleteOrder(Order order);

    List<Order> findAllByUser(User user);

    List<Order> findByUserId(String userId);
}
