
package com.example.bazar_api.service;

import com.example.bazar_api.model.Cliente;
import java.util.List;


public interface IClienteService {

    public List<Cliente> traerClientes();

    public void crearClientes(Cliente cli);

    public void borrarCliente(Long id);

    public Cliente findClientes(Long id_cliente);

    public void editarCliente(Cliente cli, Long id);
    
}
