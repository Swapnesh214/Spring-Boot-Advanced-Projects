package spring.mvc.orders.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "ORDER_ITEMS")
@Getter
@Setter
public class OrderItem {
	//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String productCode;
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
}
