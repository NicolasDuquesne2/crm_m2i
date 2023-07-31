package fr.nicolas.crm_m2i.repository;

import fr.nicolas.crm_m2i.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
