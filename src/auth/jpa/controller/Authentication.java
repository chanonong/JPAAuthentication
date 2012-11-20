package auth.jpa.controller;

import auth.jpa.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
public class Authentication {
	private static final String PERSISTENCE_UNIT = "JPAAuthentication";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	static {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
	
	public static User Authenticate(String username,String password) {
		String q = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
		Query query = em.createQuery(q);
		query.setParameter("username", username);
		query.setParameter("password", password);
		try {
			return (User) query.getSingleResult();
		} catch (Exception e) {
			StringBuilder message = new StringBuilder("Try \"SELECT u FROM User u WHERE u.username = \'");
			message.append(username).append("\' AND u.password = \'").append(password).append("\'\" [ But no result found ]");
			return null;
		}
	}
}
