package SNIICT.utils;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/*
 * Esta clase es una clase común en proyectos que utilizan hibernate
 * como su ORM y que facilita el manejo de sesiones y transacciones 
 * entre la aplicación y la base de datos
 */
public class Hibernatable {

	protected static Session session;
	
	public static void setSession(Session session)
	{
		Hibernatable.session = session;
	}
	
	public static Session getSession()
	{
		checkSession();
		return session;
	}
	
	public boolean save() {
		checkSession();
		
	    session.beginTransaction();
	    session.save(this);
	    session.getTransaction().commit();
	    return session.getTransaction().wasCommitted();
	}
	
	public boolean delete() {
		checkSession();
		
	    session.beginTransaction();
	    session.delete(this);
	    session.getTransaction().commit();
	    return session.getTransaction().wasCommitted();
	}
	
	public static final Object find(Class<?> klass, Serializable id) {
		checkSession();

        session.beginTransaction();

        Object obj = session.get(klass, id);
        
        session.getTransaction().commit();
        
        return obj;
	}
	
	public static List<?> findAll(Class<?> klass) {
		checkSession();

        session.beginTransaction();

        Criteria criteria = session.createCriteria(klass);
        List<?> results = criteria.list();

        session.getTransaction().commit();
        
        return results;
	}
	
    public static List<?> findBy(Class<?> klass, String attribute, String value){
		checkSession();
		
        session.beginTransaction();
        
        Criteria testCriteria = session.createCriteria(klass);
        testCriteria.add(Restrictions.eq(attribute, value));
        List<?> results = testCriteria.list();
        session.getTransaction().commit();
        return results;
    }
    
    protected static void checkSession()
    {
    	if (session == null || session.isOpen() == false) 
    	{
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    	}
    }
	
}
