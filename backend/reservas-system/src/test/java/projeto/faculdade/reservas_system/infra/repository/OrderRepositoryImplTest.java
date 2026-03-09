package projeto.faculdade.reservas_system.infra.repository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import projeto.faculdade.reservas_system.application.order.domain.Order;
import projeto.faculdade.reservas_system.application.user.domain.User;
import projeto.faculdade.reservas_system.infra.repository.dao.OrderDAO;
import projeto.faculdade.reservas_system.shared.utils.orders.OrderDomainUtils;
import projeto.faculdade.reservas_system.shared.utils.users.UserDomainUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings
public class OrderRepositoryImplTest {

    @Mock
    private OrderDAO orderDAO;

    @InjectMocks
    private OrderRepositoryImpl orderRepository;

    @Test
    void save_shouldCallDaoSave() {
        Order order = OrderDomainUtils.createValidOrder();
        orderRepository.save(order);
        verify(orderDAO).save(order);
    }

    @Test
    void findById_whenOrderExists_shouldReturnOrder() {
        String orderId = "123";
        Order order = OrderDomainUtils.createValidOrder();
        when(orderDAO.findById(orderId)).thenReturn(Optional.of(order));

        Optional<Order> result = orderRepository.findById(orderId);

        assertTrue(result.isPresent());
        assertEquals(order, result.get());
        verify(orderDAO).findById(orderId);
    }

    @Test
    void findById_whenOrderDoesNotExist_shouldReturnEmptyOptional() {
        String orderId = "123";
        when(orderDAO.findById(orderId)).thenReturn(Optional.empty());

        Optional<Order> result = orderRepository.findById(orderId);

        assertTrue(result.isEmpty());
        verify(orderDAO).findById(orderId);
    }

    @Test
    void deleteOrder_shouldCallDaoDelete() {
        Order order = OrderDomainUtils.createValidOrder();
        orderRepository.deleteOrder(order);
        verify(orderDAO).delete(order);
    }

    @Test
    void findAllByUser_whenOrdersExistForUser_shouldReturnOrderList() {
        User user = UserDomainUtils.createValidUser();
        Order order = OrderDomainUtils.createValidOrder();
        order.setUser(user);
        List<Order> orders = Collections.singletonList(order);
        when(orderDAO.findAll()).thenReturn(orders);

        List<Order> result = orderRepository.findAllByUser(user);

        assertEquals(1, result.size());
        assertEquals(order, result.get(0));
    }

    @Test
    void findAllByUser_whenNoOrdersExistForUser_shouldReturnEmptyList() {
        User user = UserDomainUtils.createValidUser();
        when(orderDAO.findAll()).thenReturn(Collections.emptyList());

        List<Order> result = orderRepository.findAllByUser(user);

        assertTrue(result.isEmpty());
    }
}
