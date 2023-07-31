package fr.nicolas.crm_m2i.service;


import fr.nicolas.crm_m2i.entity.Client;
import fr.nicolas.crm_m2i.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Optional<List<Client>> getAll() {
        return Optional.of(clientRepository.findAll());
    }

    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> save(Client client) {
        return Optional.of(clientRepository.save(client));
    }


    public Optional<Client> delete(Client client) {
        try {
            clientRepository.delete(client);
            return Optional.of(client);
        } catch (OptimisticLockingFailureException e) {
            return Optional.empty();
        }
    }
}
