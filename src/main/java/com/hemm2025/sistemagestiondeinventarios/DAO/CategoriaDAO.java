/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hemm2025.sistemagestiondeinventarios.DAO;

import com.hemm2025.sistemagestiondeinventarios.models.Categoria;
import com.hemm2025.sistemagestiondeinventarios.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author notpi
 */
public class CategoriaDAO {

    public boolean agregarCategoria(Categoria categoria) {
        Transaction tx = null;
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSesionFactory().openSession();
            tx = sesion.beginTransaction();
            sesion.persist(categoria);
            tx.commit(); // Confirma la transaccion y los cambios se guardan en la base de datos
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error al agregar la categoria: " + e.getMessage());
            return false;
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    public boolean eliminarCategoria(int id) {
        Transaction tx = null;
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSesionFactory().openSession();
            tx = sesion.beginTransaction();
            Categoria categoria = sesion.get(Categoria.class, id);
            if (categoria != null) {
                sesion.remove(categoria);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            // La transaction tiene algo pero algo salio mal la transaction no hace ningun cambio por el rollback
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error al eliminar la categoria: " + e.getMessage());
            return false;
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    public List<Categoria> listaCategoria() {
        List<Categoria> listaC = null;
        try {
            Session sesion = HibernateUtil.getSesionFactory().openSession();
            // Esto es HQL(Hibernate Query Language) que es como SQL pero orientado a objetos
            // Significa que trae todos los objetos de la clase Categoria y convierte el resultado en una lista 
            // de objetos Categoria que se guarda en listaC
            listaC = sesion.createQuery("from Categoria", Categoria.class).list();
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaC;
    }

    public boolean ActualizarCategoria(Categoria categoria) {
        Transaction tx = null; //Variable para menejar la transaccion 
        Session sesion = null; //Variable para manejar la conexion con la base de datos

        try {
            sesion = HibernateUtil.getSesionFactory().openSession(); // Aqui se abre una nueva sesion con la base de datos
            tx = sesion.beginTransaction(); // Se comienza una nueva transaccion
            sesion.merge(categoria);
            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error al actualizar la categoria" + e.getMessage());
            return false;
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    public Categoria getCategoria(int id) {
        Categoria categoria = null;
        try {
            Session sesion = HibernateUtil.getSesionFactory().openSession();
            categoria = sesion.get(Categoria.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return categoria;
    }

    public List<Categoria> obtenerTodas() {
        List<Categoria> categorias = new ArrayList<>();

        try (Session session = HibernateUtil.getSesionFactory().openSession()) {
            categorias = session.createQuery("from Categoria", Categoria.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categorias;
    }
}
