
package com.example.bazar_api.controller;

import com.example.bazar_api.model.Producto;
import com.example.bazar_api.service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins ={"http://localhost","*"} )
@RestController
public class ProductoController {
    
    @Autowired
    IProductoService prodServ;
    
    @GetMapping("productos")
    public List<Producto> traerProductos(){     
        return prodServ.traerProductos();
    }
    
    //Endpoint para trar un producto
    @GetMapping("productos/{codigo_producto}")
    public Producto findProducto(@PathVariable Long codigo_producto){     
        return prodServ.findProducto(codigo_producto);
    }
    
    //Endpoint para obtener todos los productos cuya cantidad_disponible sea menor a 5 
    @GetMapping("productos/falta_stock")
    public List<Producto> traerProductosFaltaStock(){     
        return prodServ.traerProductosFaltaStock();
    }
    
    @PostMapping("productos/crear")
    public String crearProductos(@RequestBody Producto prod){
        prodServ.crearProducto(prod);
        return "Producto creado correctamente";
    }
    
    @DeleteMapping("productos/eliminar/{codigo_producto}")
    public String borrarProducto(@PathVariable Long codigo_producto){
        prodServ.borrarProducto(codigo_producto);
        return "Producto borrado correctamente";
    }
    
    @PutMapping("productos/editar/{codigo_producto}")
    public Producto editarProducto(@PathVariable Long codigo_producto, @RequestBody Producto produ){       
        prodServ.editarProducto(produ);
        return prodServ.findProducto(codigo_producto);
    }
}
