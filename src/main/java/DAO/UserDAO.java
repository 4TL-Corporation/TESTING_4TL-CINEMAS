package DAO;

import java.util.List;

import Utils.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.User;

public class UserDAO {
	public void insert(User user) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.persist(user);

			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();

			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(User user) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.merge(user);

			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();

			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(String Id) throws Exception {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			User user = em.find(User.class, Id);

			if (user != null) {
				em.remove(user);
			} else {
				throw new Exception("ID can not found");
			}

			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();

			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public User findById(String Id){
		EntityManager em = JpaUtils.getEntityManager();

		User user = em.find(User.class, Id);
		em.close();
		return user;
	}
	
	public List<User> findAll(){
		EntityManager em = JpaUtils.getEntityManager();
		
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		
		return query.getResultList();
	}
	
	public List<User> findAll(int page, int pageSize){
		EntityManager em = JpaUtils.getEntityManager();
		
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		
		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);
		
		return query.getResultList();
	}
	
	public User checkLogin(String Id, String Password){
		EntityManager em = JpaUtils.getEntityManager();
		
		String jqpl = "select u from User u where u.Id = :Id and u.password = :password";
		
		TypedQuery<User> query = em.createQuery(jqpl, User.class);
		
		query.setParameter("Id", Id);
		query.setParameter("password", Password);
		
		return query.getSingleResult();
	}
	
	public List<User> findByFullname(String Fullname){
		EntityManager em = JpaUtils.getEntityManager();
		
		String jqpl = "select u from User u where u.Fullname like :Fullname";
		
		TypedQuery<User> query = em.createQuery(jqpl, User.class);
		
		query.setParameter("fullname", "%" + Fullname + "%");
		
		return query.getResultList();
	}
	
	public int countUser(){
		EntityManager em = JpaUtils.getEntityManager();
		
		String jqpl = "select count(u) from User u ";
		
		Query query = em.createQuery(jqpl);		
		
		return ((Long) query.getSingleResult()).intValue();
	}
	
	public User findByUsernameAndEmail(String username, String email) {
		EntityManager em = JpaUtils.getEntityManager();
		
		String jpql = "select u from User u where u.username =:username and u.email=:email";
		try {
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("username", username);
			query.setParameter("email", email);
			
			return query.getSingleResult();
		} finally {
			em.close();
		}
	}
}
