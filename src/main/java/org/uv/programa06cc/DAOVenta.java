package org.uv.programa06cc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Angel
 */
public class DAOVenta implements IDAOGeneral<Venta, Long> {

    @Override
    public Venta create(Venta p) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();

        session.save(p);
        for (Iterator<DetalleVenta> iterator = p.getDetalle().iterator(); iterator.hasNext();) {
            DetalleVenta dt = iterator.next();
            session.save(dt);
        }

        t.commit();
        session.close();
        return p;
    }

    @Override
    public boolean delete(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();

        Venta venta = findById(id);
        boolean b = false;
        if (venta != null) {
            for (DetalleVenta dt : venta.getDetalle()) {
                session.delete(dt);
            }
            session.delete(venta);
            t.commit();
            session.close();
            b=true;
        }
        return b;
    }

    @Override
    public Venta update(Venta p, Long id) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();

        //Buscar la venta por el id y actualizar sus valores
        Venta venta = findById(id);

        if (venta != null) {
            venta.setFecha(p.getFecha());
            venta.setMonto(p.getMonto());
            for (DetalleVenta dt: p.getDetalle()){
                session.delete(dt);
            }
            venta.setDetalle(p.getDetalle());
            session.update(venta);
            //Actualizar los detalles de la venta
            for (DetalleVenta dt : p.getDetalle()) {
                dt.setVenta(venta);
                session.save(dt);
            }
        }

        t.commit();
        session.close();
        return venta;
    } 

    @Override
    public List<Venta> findAll() {
    List<Venta> ventas = null;
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        ventas = session.createQuery("from Venta").list();
        for (Venta venta : ventas) {
            List<DetalleVenta> detalle = null;
            detalle = session.createQuery("from DetalleVenta where id=" + venta.getIdVenta()).list();
            for (DetalleVenta detalleVenta : detalle) {
                venta.getDetalle().add(detalleVenta);
            }
        }
        t.commit();
        return ventas;
    }

    @Override
    public Venta findById(Long id) {
        ArrayList<DetalleVenta> lista = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        Venta p = session.get(Venta.class, id);
        
        if (p != null){
            for(DetalleVenta dt: p.getDetalle()){
            lista.add(dt);
        }
        }
        else{
            session.close();
            return null;
        }
       
        t.commit();
        session.close();
        return p;
    }

}
