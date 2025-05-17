/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hemm2025.sistemagestiondeinventarios.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author notpi
 */
public class HibernateUtil {
   private static final SessionFactory sesion = new Configuration().configure().buildSessionFactory();
   public static SessionFactory getSesionFactory(){
       return sesion;
   }
}
