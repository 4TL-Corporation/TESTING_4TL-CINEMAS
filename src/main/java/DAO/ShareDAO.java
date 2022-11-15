package DAO;

import java.util.List;

import Utils.JpaUtils;
import domain.ShareReport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Share;

public class ShareDAO {
	public void insert(Share Share) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.persist(Share);

			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();

			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(Share Share) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.merge(Share);

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

			Share Share = em.find(Share.class, Id);

			if (Share != null) {
				em.remove(Share);
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

	public Share findById(String Id) {
		EntityManager em = JpaUtils.getEntityManager();

		Share Share = em.find(Share.class, Id);

		return Share;
	}

	public List<Share> findAll() {
		EntityManager em = JpaUtils.getEntityManager();

		TypedQuery<Share> query = em.createNamedQuery("Share.findAll", Share.class);

		return query.getResultList();
	}

	public List<Share> findAll(int page, int pageSize) {
		EntityManager em = JpaUtils.getEntityManager();

		TypedQuery<Share> query = em.createNamedQuery("Share.findAll", Share.class);

		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);

		return query.getResultList();
	}

	public List<Share> findByFullname(String Fullname) {
		EntityManager em = JpaUtils.getEntityManager();

		String jqpl = "select u from Share u where u.Fullname like :Fullname";

		TypedQuery<Share> query = em.createQuery(jqpl, Share.class);

		query.setParameter("fullname", "%" + Fullname + "%");

		return query.getResultList();
	}

	public int countShare() {
		EntityManager em = JpaUtils.getEntityManager();

		String jqpl = "select count(u) from Share u ";

		Query query = em.createQuery(jqpl);

		return ((Long) query.getSingleResult()).intValue();
	}
	
	public List<ShareReport> reportShareByVideos(String videoID) {
		String jpql = "SELECT new domain.ShareReport(s.user.username, s.video.videoID, s.emails, s.shareDate) "
				+ " FROM Share s "
				+ " WHERE s.video.videoID = :videoID";

		EntityManager em = JpaUtils.getEntityManager();

		List<ShareReport> list = null;

		try {
			TypedQuery<ShareReport> query = em.createQuery(jpql, ShareReport.class);

			query.setParameter("videoID", videoID);

			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}
}
