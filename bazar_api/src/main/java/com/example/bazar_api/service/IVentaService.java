
package com.example.bazar_api.service;

import com.example.bazar_api.dto.VentaDTO;
import com.example.bazar_api.model.Producto;
import com.example.bazar_api.model.Venta;
import java.time.LocalDate;
import java.util.List;


public interface IVentaService {

    public List<Venta> traerVentas();

    public Venta findVenta(Long codigo_venta);

    public String crearVentas(Venta vent);

    public void borrarVenta(Long codigo_venta);

    public void editarVenta(Venta vent, Long codigo_venta);

    public List<Producto> findVentaProductos(Long codigo_venta);

    public Venta calcularTotalVenta(Venta vent);

    public String findVentasFecha(LocalDate fecha_venta);

    public VentaDTO findVentaDTOMayorMonto();

    public void vaciarListaProductos(Long codigo_venta);
    
}
