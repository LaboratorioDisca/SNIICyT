package SNIICT.utils;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.apache.log4j.Logger;  

/*
 * Esta clase es una clase común en proyectos que utilizan hibernate
 * como su ORM y que facilita el manejo de sesiones y transacciones 
 * entre la aplicación y la base de datos
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final Logger LOG = Logger.getLogger(HibernateUtil.class);  
    
    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static SessionFactory newSessionFactory(final String pathToHibernateCfgXml) {  
        LOG.info("Loading Hibernate Session Factory with configurations from file "   
                + pathToHibernateCfgXml + "...");  
        Configuration hibernateConfiguration = new Configuration();  
        hibernateConfiguration.configure(pathToHibernateCfgXml);  
        return hibernateConfiguration.buildSessionFactory();  
    }  
}
