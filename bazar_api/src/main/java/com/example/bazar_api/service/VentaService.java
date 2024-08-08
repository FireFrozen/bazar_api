
package com.example.bazar_api.service;

import com.example.bazar_api.dto.VentaDTO;
import com.example.bazar_api.model.Producto;
import com.example.bazar_api.model.Venta;
import com.example.bazar_api.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    @Autowired
    IVentaRepository ventaRepo;
    
    @Autowired
    ProductoService prodServ;
    
    @Override
    public List<Venta> traerVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta findVenta(Long codigo_venta) {
        return ventaRepo.findById(codigo_venta).orElse(null);
    }

    @Override
    public String crearVentas(Venta vent) {
        //obtenemos el total antes de crear la venta
        vent = calcularTotalVenta(vent);
        //ventaRepo.save(vent);
        
        String mensaje = "Venta creada correctamente";
        
        //logica para descontar productos del stock
        List<Venta>listaVentas = this.traerVentas();
        
        //Verificamos si hay stock disponible
        List<Producto>listaProductos = vent.getListaProductos();
        boolean hayStock = true;
        
        for(Producto prod : listaProductos){
            //Obtenemos el producto de la DB con el id del producto
            prod = prodServ.findProducto(prod.getCodigo_producto());
            if(prod.getCantidad_disponible()>0){
                prod.setCantidad_disponible(prod.getCantidad_disponible()-1);
                //prodServ.editarProducto(prod);
            }
            else{
                mensaje = "La venta no se pudo crear debido a Stock insuficiente";
                hayStock = false;
                break;
            }
            //System.out.println(prod.toString());
        }
        
        if (hayStock){ 
            ventaRepo.save(vent);
            
            /*for(Producto prod : listaProductos){                
                prodServ.editarProducto(prod);
            }*/
            //La ultima venta es la que acabamos de agregar
            vent = listaVentas.get(listaVentas.size()-1);
            listaProductos = vent.getListaProductos();
            for(Producto prod : listaProductos){

                prod.setCantidad_disponible(prod.getCantidad_disponible()-1);
                prodServ.editarProducto(prod);
                //System.out.println(prod.toString());
            }
        }
        
        return mensaje;
    }

    @Override
    public void borrarVenta(Long codigo_venta) {
       
        //logica para reponer el stock
        Venta vent = this.findVenta(codigo_venta);      
        List<Producto>listaProductos = vent.getListaProductos();
        
        for(Producto prod:listaProductos){
            prod.setCantidad_disponible(prod.getCantidad_disponible()+1);
        }
        
        //Borramos la venta despues de reponer el stock
        ventaRepo.deleteById(codigo_venta);
    }

    @Override
    public void editarVenta(Venta vent) {
        ventaRepo.save(vent);
    }

    @Override
    public List<Producto> findVentaProductos(Long codigo_venta) {
        return this.findVenta(codigo_venta).getListaProductos();
    }

    @Override
    public Venta calcularTotalVenta(Venta vent) {
        List<Producto> listaProductos = vent.getListaProductos();
        Double total = 0.0;
        
        for (Producto prod:listaProductos){
            total = total + prodServ.findProducto(prod.getCodigo_producto()).getCosto();
        }
        vent.setTotal(total);
        return vent;
    }

    @Override
    public String findVentasFecha(LocalDate fecha_venta) {
        List<Venta>listaVentas = this.traerVentas();
        int cont = 0;
        double montoTotal = 0.0;
        
        for(Venta venta:listaVentas){
            if(fecha_venta.equals(venta.getFecha_venta()) ){
                cont++;
                montoTotal = montoTotal + venta.getTotal();
            }
        }
        
        return "La sumatoria del monto del día es: " + montoTotal + " soles <br>"+
               "La cantidad total de ventas del día: " + cont;
    }

    @Override
    public VentaDTO findVentaDTOMayorMonto() {
        VentaDTO ventaDTO = new VentaDTO();
        
        List<Venta> listaVentas = this.traerVentas();
        double mayorTotal = 0;
        Long idMayor = 0L;
        
        for(Venta venta:listaVentas){
            if (venta.getTotal()>mayorTotal){
                idMayor = venta.getCodigo_venta();
            }
        }
        
        Venta ventaMayorTotal = this.findVenta(idMayor);
        int cantProductos = 0;
        
        for(Producto prod:ventaMayorTotal.getListaProductos()){
            cantProductos++;
        }
        
        ventaDTO.setCodigo_venta(idMayor);
        ventaDTO.setApellidoCliente(ventaMayorTotal.getUnCliente().getApellido());
        ventaDTO.setNombreCliente(ventaMayorTotal.getUnCliente().getNombre());
        ventaDTO.setTotal(ventaMayorTotal.getTotal());
        ventaDTO.setCantidadDeProductos(cantProductos);
                
        return ventaDTO;
    }

    @Override
    public void vaciarListaProductos(Long codigo_venta) {
        Venta venta = this.findVenta(codigo_venta);
        List<Producto> listaProductos = venta.getListaProductos();
        listaProductos.clear();
        venta.setListaProductos(listaProductos);
        this.editarVenta(venta);
    }
}
