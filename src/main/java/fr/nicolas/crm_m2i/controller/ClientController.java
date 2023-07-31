package fr.nicolas.crm_m2i.controller;


import fr.nicolas.crm_m2i.entity.Client;
import fr.nicolas.crm_m2i.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("clients")
    public ResponseEntity<List<Client>> getAll() {
        Optional<List<Client>> optionalClients = clientService.getAll();

        if (optionalClients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalClients.get());
    }

    @GetMapping("clients/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        Optional<Client> optionalClient = clientService.getById(id);

        if (optionalClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalClient.get());
    }


    @PostMapping("clients")
    public ResponseEntity<Client> post(@RequestBody Client client) {

        if(client.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Client> optionalClient = clientService.save(client);

        if (optionalClient.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(optionalClient.get());
    }

    @PutMapping("clients/{id}")
    public ResponseEntity<Client> put(@RequestBody Client client, @PathVariable Long id) {

        if (client.getId() != id ||
                client.getId() == null ||
                client.getCompanyName() == null ||
                client.getFirstName() == null ||
                client.getLastName() == null ||
                client.getEmail() == null ||
                client.getPhone() == null ||
                client.getAddress() == null ||
                client.getZipCode() == null ||
                client.getCity() == null ||
                client.getCountry() == null ||
                client.getState() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Client> optionalClient = clientService.getById(id);

        if (optionalClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        optionalClient = clientService.save(client);

        if (optionalClient.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(optionalClient.get());
    }


    @DeleteMapping("clients/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id) {
        Optional<Client> optionalClient = clientService.getById(id);

        if (optionalClient.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        optionalClient = clientService.delete(optionalClient.get());

        if (optionalClient.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(optionalClient.get());
    }

}
