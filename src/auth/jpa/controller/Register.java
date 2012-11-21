package auth.jpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import auth.jpa.model.User;

public class Register {
	private static final String PERSISTENCE_UNIT = "JPAAuthentication";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	static {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
	
	public static User regis(String username,String password,String name,String lastname) {
		User user = new User(username,password,name,lastname);
		String q = "SELECT u FROM User u WHERE u.username = :username";
		Query query = em.createQuery(q);
		query.setParameter("username", username);
		try {
			User tmpuser = (User) query.getSingleResult();
			if(tmpuser == null) {
				EntityTransaction et = em.getTransaction();
				et.begin();
				em.persist(user);
				et.commit();
			}
		} catch (Exception e) {
			StringBuilder message = new StringBuilder("Try \"SELECT u FROM User u WHERE u.username = \'");
			message.append(username);
		}
		return null;
	}
}
