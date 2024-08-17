
package com.example.bazar_api.service;

import com.example.bazar_api.model.Producto;
import com.example.bazar_api.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    IProductoRepository prodRepo;

    @Override
    public List<Producto> traerProductos() {
        return prodRepo.findAll();
    }

    @Override
    public Producto findProducto(Long id_producto) {
        return prodRepo.findById(id_producto).orElse(null);
    }

    @Override
    public void crearProducto(Producto prod) {
        prodRepo.save(prod);
    }

    @Override
    public void borrarProducto(Long id) {
       prodRepo.deleteById(id);
    }

    @Override
    public void editarProducto(Producto produ, Long codigo_producto) {
       prodRepo.save(produ);
    }

    @Override
    public List<Producto> traerProductosFaltaStock() {
        List<Producto> listaProductosFaltaStock = new ArrayList<>();
        List<Producto> listaProductos = this.traerProductos();
        
        for(Producto prod:listaProductos){
            if(prod.getCantidad_disponible()<=5){
                listaProductosFaltaStock.add(prod);
            }
        }
        
        return listaProductosFaltaStock;
    }
    
    
}
