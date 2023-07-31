package fr.nicolas.crm_m2i.controller;

import fr.nicolas.crm_m2i.entity.Order;
import fr.nicolas.crm_m2i.service.ClientService;
import fr.nicolas.crm_m2i.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin()
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ClientService clientService;

    @GetMapping("orders")
    public ResponseEntity<List<Order>> getAll() {
        Optional<List<Order>> optionalOrders = orderService.getAll();

        if(optionalOrders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalOrders.get());
    }

    @GetMapping("orders/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderService.getById(id);

        if(optionalOrder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalOrder.get());
    }


    @PostMapping("orders")
    public ResponseEntity<Order> post(@RequestBody Order order) {

        if(order.getId() != null ||
                clientService.getById(order.getClient().getId()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Order> optionalOrder = orderService.save(order);

        if(optionalOrder.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(optionalOrder.get());
    }

    @PutMapping("orders/{id}")
    public ResponseEntity<Order> put(@RequestBody Order order, @PathVariable Long id) {

        if(order.getId() != id ||
                order.getId() == null ||
                order.getClient() == null ||
                order.getDesignation() == null ||
                order.getState() == null ||
                order.getTypePresta() == null ||
                order.getNbDays() == null||
                order.getUnitPrice() == null ||
                clientService.getById(order.getClient().getId()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }


        Optional<Order> optionalOrder = orderService.getById(id);

        if(optionalOrder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        optionalOrder = orderService.save(order);

        if(optionalOrder.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(optionalOrder.get());
    }

    @DeleteMapping("orders/{id}")
    public ResponseEntity<Order> delete(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderService.getById(id);

        if(optionalOrder.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        optionalOrder = orderService.delete(optionalOrder.get());

        if(optionalOrder.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(optionalOrder.get());
    }

}
