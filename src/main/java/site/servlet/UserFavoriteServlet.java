package site.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import DAO.UserDAO;
import common.PageInfo;
import common.PageType;
import common.SessionUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Favorite;
import model.User;

@WebServlet("/UserFavorite")
public class UserFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = SessionUtils.getLoginUsername(req);
		showInfoUser(username, req, resp);
		findVideosByUser(username, req, resp);
		PageInfo.prepareAndForwardSite(req, resp, PageType.SITE_USER_FAVORITE);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	protected void findVideosByUser(String username, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDAO usdao = new UserDAO();
		User user = usdao.findById(username);
		if (user != null) {
			List<Favorite> favorites = user.getFavorites();
//			req.setAttribute("user", user);
			
			//Sort lượt views giảm dần
			if (!favorites.isEmpty()) {
				Collections.sort(favorites, new Comparator<Favorite>() {

					@Override
					public int compare(Favorite o1, Favorite o2) {
						// TODO Auto-generated method stub
						return o2.getVideo().getViews() - o1.getVideo().getViews();
					}
					
				});
				req.setAttribute("movieFav", favorites);
				req.setAttribute("countVideoFav", favorites.size());
			} else {
//				req.setAttribute("error", "Khong tim thay!");
			}
		}

//		FavoriteDAO dao = new FavoriteDAO();
//		List<UserFavorite> list = dao.findVideosFavoriteByUserID(username);

	}

	protected void showInfoUser(String username, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		UserDAO dao = new UserDAO();

		User user = dao.findById(username);
		if (user.getAdmin()) {
			req.setAttribute("adminRole", user);
		}
		
		String fullname = user.getFullname();
		int i = fullname.lastIndexOf(" ");
		if (i > 0) {
			req.setAttribute("firstname", fullname.substring(i));
		}
		req.setAttribute("user", user);

	}
}
