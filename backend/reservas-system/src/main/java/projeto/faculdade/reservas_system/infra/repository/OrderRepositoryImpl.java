package projeto.faculdade.reservas_system.infra.repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import projeto.faculdade.reservas_system.application.order.domain.Order;
import projeto.faculdade.reservas_system.application.order.port.OrderRepository;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.infra.repository.dao.OrderDAO;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDAO dao;

    @Override
    public Order save(Order order) {
        log.info("Saving new order for user: {}", order.getUser().getEmail());
        return dao.save(order);
    }

    @Override
    public Optional<Order> findById(String id) {
        log.info("Finding order with id: {}", id);
        return dao.findById(id);
    }

    @Override
    public void deleteOrder(Order order) {
        log.info("Deleting order for user: {} ", order.getUser().getEmail());
        dao.delete(order);
    }

    @Override
    public List<Order> findAllByUser(User user) {
        log.info("Finding all orders for user: {}", user.getEmail());
        return dao.findByUserId(user.getId());
    }

    @Override
    public List<Order> findByUserId(String userId) {
        log.info("Finding all orders for user id: {}", userId);
        return dao.findByUserId(userId);
    }

    @Override
    public List<Order> findAll() {
        log.info("Finding all orders");
        return dao.findAll();
    }
}
