
package com.example.bazar_api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_venta;
    
    @Temporal(TemporalType.DATE)
    private LocalDate fecha_venta ;
    private Double total ;
    
    //@OneToMany
    @ManyToMany//(cascade = CascadeType.ALL)
    @JoinTable(
        name = "venta_producto",
        joinColumns = @JoinColumn(name = "codigo_venta"),
        inverseJoinColumns = @JoinColumn(name = "codigo_producto")
    )
    private List<Producto> listaProductos ;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente unCliente ;

    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }
    
    
}
