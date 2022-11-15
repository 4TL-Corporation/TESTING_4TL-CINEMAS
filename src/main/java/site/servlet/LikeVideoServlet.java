package site.servlet;

import java.io.IOException;

import DAO.FavoriteDAO;
import common.SessionUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Favorite;

/**
 * Servlet implementation class LikeVideoServlet
 */
@WebServlet("/LikeVideo")
public class LikeVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// kiem tra trang thai dang nhap
		if (!SessionUtils.isLogin(req)) {
			resp.sendRedirect("Homepage");
			return;
		}

		String action = req.getParameter("action");
		String videoID = req.getParameter("id");
		String username = SessionUtils.getLoginUsername(req);

		// kiem tra video ID
		if (videoID == null) {
			resp.sendRedirect("Homepage");
			return;
		}

		// thuc hien like va them du lieu vao DB
		switch (action) {
			case "like": {
				doLike(username, videoID, req, resp);
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doLike(String username, String videoID, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Kiểm tra tồn tại Fav
		FavoriteDAO daocheck = new FavoriteDAO();
		Favorite existFav = daocheck.findByUserIDAndVideoID(username, videoID);

		// Nếu có tồn tại thì thực hiện update
		if (existFav != null) {
			try {
				// Update Like or Dislike
				FavoriteDAO dao = new FavoriteDAO();
				boolean result = dao.updateLikeOrDislike(existFav);

				System.out.println(result + " check result");
				if (result == true) {
					req.setAttribute("btnLike", true);
				} else {
					req.setAttribute("btnLike", false);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		resp.sendRedirect("MovieSingle?id=" + videoID);
	}
}
