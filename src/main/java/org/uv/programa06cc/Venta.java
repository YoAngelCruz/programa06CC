/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.programa06cc;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 * @author Angel
 */

@Entity
@Table(name = "Venta")
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long idVenta;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "monto")
    private double monto;
    
    @OneToMany(mappedBy = "venta", fetch = FetchType.LAZY )
    private Set<DetalleVenta> detalle;

    

    public Venta() {
        detalle=new HashSet<>();
    }

    public Set<DetalleVenta> getDetalle() {
        return detalle;
    }

    public void setDetalle(Set<DetalleVenta> detalle) {
        this.detalle = detalle;
    }
    

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
}
