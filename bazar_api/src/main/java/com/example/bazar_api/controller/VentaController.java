
package com.example.bazar_api.controller;

import com.example.bazar_api.dto.VentaDTO;
import com.example.bazar_api.model.Producto;
import com.example.bazar_api.model.Venta;
import com.example.bazar_api.service.IVentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {
    @Autowired
    IVentaService ventaServ;
   
    @GetMapping("ventas")
    public List<Venta> traerVentas(){     
        return ventaServ.traerVentas();
    }
    
    //Endpoint para traer una venta
    @GetMapping("ventas/{codigo_venta}")
    public Venta findVenta(@PathVariable Long codigo_venta){     
        return ventaServ.findVenta(codigo_venta);
    }
    
    //Endpoint para Obtener la lista de productos de una determinada venta 
    @GetMapping("ventas/productos/{codigo_venta}")
    public List<Producto> findVentaProductos(@PathVariable Long codigo_venta){     
        return ventaServ.findVenta(codigo_venta).getListaProductos();
    }
    
    //Endpoint para obtener la sumatoria del monto y 
    //también cantidad total de ventas de un determinado día 
    @GetMapping("ventas/dia/{fecha_venta}")
    public String findVentasFecha(@PathVariable LocalDate fecha_venta){     
        return ventaServ.findVentasFecha(fecha_venta);
    }
    
    @PostMapping("ventas/crear")
    public String crearVentas(@RequestBody Venta vent){
        //incluye decuento de stock (en proceso)
        return ventaServ.crearVentas(vent);
        //return "Venta creada correctamente";
    }
    
    @DeleteMapping("ventas/eliminar/{codigo_venta}")
    public String borrarVenta(@PathVariable Long codigo_venta){    
        //vaciamos la lista de productos para que nos permita eiminar la venta
        //ventaServ.vaciarListaProductos(codigo_venta);
        ventaServ.borrarVenta(codigo_venta);
        return "Venta borrada correctamente";
    }
    
    @PutMapping("ventas/editar/{codigo_venta}")
    public Venta editarVenta(@PathVariable Long codigo_venta, @RequestBody Venta vent){       
        ventaServ.editarVenta(vent);
        return ventaServ.findVenta(codigo_venta);
    }
    
    //Endpoint para obtener la venta con el monto más alto de todas con el patron VentaDTO
    @GetMapping("ventas/mayor_venta")
    public VentaDTO findVentaDTOMayorMonto(){     
        return ventaServ.findVentaDTOMayorMonto();
    }
}
