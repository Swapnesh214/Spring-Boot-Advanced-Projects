package spring.mvc.security.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	private boolean disabled;

	@OneToMany(mappedBy = "user")
	private Set<Address> addresses;

	public User() {}

	public User(Integer id, String name, String email, boolean disabled) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.disabled = disabled;
	}

	public User(Integer id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
}
