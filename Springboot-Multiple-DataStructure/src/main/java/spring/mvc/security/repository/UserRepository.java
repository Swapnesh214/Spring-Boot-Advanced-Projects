package spring.mvc.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {}
