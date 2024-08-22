
package com.example.bazar_api.controller;

import com.example.bazar_api.model.Cliente;
import com.example.bazar_api.service.IClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteController {
    
    @Autowired
    IClienteService clienteServ;

    @GetMapping("clientes")
    public List<Cliente> traerClientes(){
        
        return clienteServ.traerClientes();
    }
    
    //Endpoint para trar un cliente
    @GetMapping("clientes/{id_cliente}")
    public Cliente findClientes(@PathVariable Long id_cliente){
        
        return clienteServ.findClientes(id_cliente);
    }

    //Endpoint para trar un cliente por su DNI
    @GetMapping("clientes/dni/{dni}")
    public Cliente findClientesbyDni(@PathVariable String dni){

        return clienteServ.findClientesbyDni(dni);
    }
    
    @PostMapping("clientes/crear")
    public String crearClientes(@RequestBody Cliente cli){
        clienteServ.crearClientes(cli);
        return "Cliente creado correctamente";
    }
    
    @DeleteMapping("clientes/eliminar/{id}")
    public String borrarClientes(@PathVariable Long id){
        clienteServ.borrarCliente(id);
        return "Cliente borrado correctamente";
    }
    
    @PutMapping("clientes/editar/{id}")
    public Cliente editarClientes(@PathVariable Long id, @RequestBody Cliente cli){       
        clienteServ.editarCliente(cli, id);
        return clienteServ.findClientes(id);
    }
}
