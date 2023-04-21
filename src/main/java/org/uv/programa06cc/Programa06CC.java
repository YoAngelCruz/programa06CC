package org.uv.programa06cc;

import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Angel
 */
public class Programa06CC {

    public static void main(String[] args) {
        DAOVenta dao =new DAOVenta();
        Venta venta = new Venta();
        
        DetalleVenta d1= new DetalleVenta();
        //DetalleVenta d2 = new DetalleVenta();
        
//        Date fecha = Date.from(Instant.now());
//        venta.setFecha(new java.sql.Date(fecha.getTime()));
//        venta.setMonto(100.0);
        
        d1.setDescripcion("pro 3");
        d1.setCantidad(2.0);
        d1.setPrecio(20.0);
        d1.setSubtotal(40.0);
        d1.setVenta(venta);
        venta.getDetalle().add(d1);
        
//        d2.setDescripcion("pro 2");
//        d2.setCantidad(1.0);
//        d2.setPrecio(10.0);
//        d2.setSubtotal(10.0);
//        d2.setVenta(venta);
//        venta.getDetalle().add(d2);
        
       //dao.delete(2L);
       
       dao.update(venta, 3L);
        
       //Venta venta = dao.findById(3L);
       
       //dao.create(venta);
        
        System.out.println("aaaaa");
    }
}
