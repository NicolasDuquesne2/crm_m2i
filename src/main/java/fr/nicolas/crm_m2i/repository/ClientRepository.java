package fr.nicolas.crm_m2i.repository;

import fr.nicolas.crm_m2i.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
