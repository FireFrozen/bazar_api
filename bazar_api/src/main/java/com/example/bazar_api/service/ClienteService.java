
package com.example.bazar_api.service;

import com.example.bazar_api.model.Cliente;
import com.example.bazar_api.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    IClienteRepository clienteRepo;
    
    @Override
    public List<Cliente> traerClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public void crearClientes(Cliente cli) {
        clienteRepo.save(cli);
    }

    @Override
    public void borrarCliente(Long id) {
        clienteRepo.deleteById(id);
    }

    @Override
    public Cliente findClientes(Long id_cliente) {
        return clienteRepo.findById(id_cliente).orElse(null);
    }

    @Override
    public void editarCliente(Cliente cli) {
       clienteRepo.save(cli);
    }
    
}
