
package com.example.bazar_api.service;

import com.example.bazar_api.model.Cliente;
import com.example.bazar_api.model.Producto;
import java.util.List;


public interface IProductoService {

    public List<Producto> traerProductos();

    public Producto findProducto(Long id_producto);

    public void crearProducto(Producto prod);

    public void borrarProducto(Long id);

    public void editarProducto(Producto produ, Long codigo_producto);

    public List<Producto> traerProductosFaltaStock();
    
}
