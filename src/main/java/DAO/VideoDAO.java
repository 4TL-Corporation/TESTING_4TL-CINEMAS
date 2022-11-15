package DAO;

import java.util.List;

import Utils.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Video;

public class VideoDAO {
	public void insert(Video Video) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			
			em.persist(Video);

			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();

			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(Video Video) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.merge(Video);

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

			Video Video = em.find(Video.class, Id);

			if (Video != null) {
				em.remove(Video);
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

	public Video findById(String Id) {
		EntityManager em = JpaUtils.getEntityManager();

		Video Video = em.find(Video.class, Id);

		return Video;
	}

	public List<Video> findAll() {
		EntityManager em = JpaUtils.getEntityManager();

		TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);

		return query.getResultList();
	}

	public List<Video> findAll(int page, int pageSize) {
		EntityManager em = JpaUtils.getEntityManager();

		TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);

		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

	public List<Video> findByFullname(String Title) {
		EntityManager em = JpaUtils.getEntityManager();

		String jqpl = "select u from Video u where u.Title like :Title";

		TypedQuery<Video> query = em.createQuery(jqpl, Video.class);

		query.setParameter("Title", "%" + Title + "%");

		return query.getResultList();
	}

	public int countVideo() {
		EntityManager em = JpaUtils.getEntityManager();

		String jqpl = "select count(u) from Video u ";

		Query query = em.createQuery(jqpl);

		return ((Long) query.getSingleResult()).intValue();
	}
	
	public List<Video> findRandom3Videos() {
		EntityManager em = JpaUtils.getEntityManager();

		TypedQuery<Video> query = em.createNamedQuery("Video.random3",  Video.class);
		
		return query.getResultList();
	}

}
