/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hemm2025.sistemagestiondeinventarios.DAO;

import com.hemm2025.sistemagestiondeinventarios.models.Producto;
import com.hemm2025.sistemagestiondeinventarios.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author notpi
 */
public class ProductoDAO {

    public boolean agregarProducto(Producto producto) {
        Transaction tx = null;
        Session sesion = null;

        try {
            sesion = HibernateUtil.getSesionFactory().openSession();
            tx = sesion.beginTransaction();
            System.out.println("Producto a guardar: " + producto);
            sesion.persist(producto);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error al agregar el producto: " + e.getMessage());
            e.printStackTrace();

            return false;
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    public boolean eliminarProducto(int id) {
        Transaction tx = null;
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSesionFactory().openSession();
            tx = sesion.beginTransaction();
            Producto producto = sesion.get(Producto.class, id);
            if (producto != null) {
                sesion.remove(producto);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error al eliminar el producto : " + e.getMessage());
            return false;
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    public List<Producto> listaProductos() {
        List<Producto> listaP = null;
        try {
            Session sesion = HibernateUtil.getSesionFactory().openSession();
            listaP = sesion.createQuery("from Producto", Producto.class).list();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaP;
    }

    public boolean actualizarProducto(Producto producto) {
        Transaction tx = null;
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSesionFactory().openSession();
            tx = sesion.beginTransaction();
            sesion.merge(producto);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error al actualizar el producto: " + e.getMessage());
            return false;
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    public Producto getProducto(int id) {
        Producto producto = null;
        try {
            Session sesion = HibernateUtil.getSesionFactory().openSession();
            producto = sesion.get(Producto.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return producto;
    }

    public Producto obtenerPorId(int id) {
        Producto producto = null;
    try (Session session = HibernateUtil.getSesionFactory().openSession()) {
        producto = session.get(Producto.class, id);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return producto;
    }

    public boolean actualizar(Producto producto) {
        Session session = HibernateUtil.getSesionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(producto);
            tx.commit();
            return true;  // Si la actualización fue exitosa
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();  // Si hubo un error, revertimos la transacción
            }
            e.printStackTrace();  // O puedes registrar el error
            return false;  // Si hubo un error
        } finally {
            session.close();  // Asegurarse de cerrar la sesión
        }
    }

    public List<Producto> obtenerInventarioCompleto() {
        Session sesion = HibernateUtil.getSesionFactory().openSession();
        // "FROM Producto p WHERE p.stock > 0" <- para filtrar por solo disponibles
        List<Producto> productos = sesion.createQuery("FROM Producto", Producto.class).list();
        sesion.close();
        return productos;
    }
    


}
