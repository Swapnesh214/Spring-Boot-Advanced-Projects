package spring.mvc.service.entity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mvc.orders.entities.Order;
import spring.mvc.orders.repository.OrderRepository;
import spring.mvc.security.entity.User;
import spring.mvc.security.repository.UserRepository;

@Service
public class UserOrdersService {
	//
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(transactionManager = "securityTransactionManager")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Transactional(transactionManager = "ordersTransactionManager")
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}
}
