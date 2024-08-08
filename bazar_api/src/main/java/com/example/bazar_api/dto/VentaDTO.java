
package com.example.bazar_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaDTO {
    private Long codigo_venta;
    private Double total;
    private int cantidadDeProductos;
    private String nombreCliente;
    private String apellidoCliente;

    public VentaDTO() {
    }

    public VentaDTO(Long codigo_venta, Double total, int cantidadDeProductos, String nombreCliente, String apellidoCliente) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantidadDeProductos = cantidadDeProductos;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
    }
    
    
}
