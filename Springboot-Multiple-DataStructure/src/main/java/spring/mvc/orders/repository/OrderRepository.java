package spring.mvc.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.orders.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {}
