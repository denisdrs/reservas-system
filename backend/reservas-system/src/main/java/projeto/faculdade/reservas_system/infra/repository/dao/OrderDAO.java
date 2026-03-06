package projeto.faculdade.reservas_system.infra.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.faculdade.reservas_system.application.order.domain.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, String> {
}
