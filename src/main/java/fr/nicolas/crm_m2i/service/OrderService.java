package fr.nicolas.crm_m2i.service;

import fr.nicolas.crm_m2i.entity.Order;
import fr.nicolas.crm_m2i.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Optional<List<Order>> getAll() {
        return Optional.of(orderRepository.findAll());
    }

    public Optional<Order> getById(Long id) {
        return orderRepository.findById(id);
    }

    public Optional<Order> save(Order order) {
        return Optional.of(orderRepository.save(order));
    }


    public Optional<Order> delete(Order order) {
        try {
            orderRepository.delete(order);
            return Optional.of(order);
        } catch (OptimisticLockingFailureException e) {
            return Optional.empty();
        }
    }
}
