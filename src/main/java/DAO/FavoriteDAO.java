package DAO;

import java.sql.Date;
import java.util.List;

import Utils.JpaUtils;
import domain.FavoriteReport;
import domain.FavoriteUserReport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Favorite;

public class FavoriteDAO extends AbstractEntityDAO<Favorite> {

	public FavoriteDAO() {
		super(Favorite.class);
	}

	public void insert(Favorite favorite) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.persist(favorite);

			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();

			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(Favorite favorite) {
		EntityManager em = JpaUtils.getEntityManager();

		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			em.merge(favorite);

			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();

			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public List<FavoriteUserReport> reportUsersByVideos(String videoID) {
		String jpql = "select new domain.FavoriteUserReport(f.user.username, f.user.fullname, "
				+ "f.user.email, f.likeDate) from Favorite f where f.video.videoID = :videoID";

		EntityManager em = JpaUtils.getEntityManager();

		List<FavoriteUserReport> list = null;

		try {
			TypedQuery<FavoriteUserReport> query = em.createQuery(jpql, FavoriteUserReport.class);

			query.setParameter("videoID", videoID);

			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}

	public List<FavoriteReport> reportByVideos() {
		String jpql = "select new domain.FavoriteReport(f.video.title, count(f), min(f.likeDate), max(f.likeDate)) "
				+ " from Favorite f group by f.video.title ";

		EntityManager em = JpaUtils.getEntityManager();

		List<FavoriteReport> list = null;

		try {
			TypedQuery<FavoriteReport> query = em.createQuery(jpql, FavoriteReport.class);

			list = query.getResultList();
		} finally {
			em.close();
		}
		return list;
	}

	public Favorite findByUserIDAndVideoID(String username, String videoID) {

		EntityManager em = JpaUtils.getEntityManager();
		try {
			String jqpl = "select u from Favorite u where u.user.username = :user and u.video.videoID = :video ";

			TypedQuery<Favorite> query = em.createQuery(jqpl, Favorite.class);

			query.setParameter("user", username);
			query.setParameter("video", videoID);

			Favorite favorite = new Favorite();

			favorite = query.getSingleResult();
			return favorite;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}

	public boolean updateLikeOrDislike(Favorite existFav) {

		// Thiết lập giá trị Like và ngày like cho Fav đã tồn tại
		if (existFav.getIsLiked() == false) {
			existFav.setIsLiked(true);
			existFav.setLikeDate(new Date(System.currentTimeMillis()));
		} else {
			existFav.setIsLiked(false);
			existFav.setLikeDate(null);
		}

		try {
			// Cập nhật Fav đã tồn tại

			FavoriteDAO daoFav = new FavoriteDAO();
			daoFav.update(existFav);
			System.out.println("updated");
			return daoFav != null ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	

}
