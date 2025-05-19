package com.hemm2025.sistemagestiondeinventarios.DAO;

import com.hemm2025.sistemagestiondeinventarios.models.Usuario;
import com.hemm2025.sistemagestiondeinventarios.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class UsuarioDAO {
    public void guardar(Usuario usuario){
        try(Session sesion = HibernateUtil.getSesionFactory().openSession()) {
            Transaction tx = sesion.beginTransaction();
            if(usuario.getId() == 0 ){
            sesion.persist(usuario);                   
            }else{
                sesion.merge(usuario);
            }tx.commit();
        } 
    }
    public Usuario getusuario(String username, String password){
        Usuario usuario = null;
        Session sesion = HibernateUtil.getSesionFactory().openSession();
        try {
            String hql = "FROM Usuario WHERE username = :username AND password = :password";
            Query<Usuario> query = sesion.createQuery(hql,Usuario.class);
            
            query.setParameter("username", username);
            query.setParameter("password", password);
            
            usuario = query.uniqueResult();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            sesion.close();
        }
        return usuario;
    }
    
    public Usuario buscarPorUsername(String username) {
    Usuario usuario = null;
    try (Session session = HibernateUtil.getSesionFactory().openSession()) {
        Query<Usuario> query = session.createQuery("FROM Usuario WHERE username = :username", Usuario.class);
        query.setParameter("username", username);
        usuario = query.uniqueResult();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return usuario;
}
    
    
}
