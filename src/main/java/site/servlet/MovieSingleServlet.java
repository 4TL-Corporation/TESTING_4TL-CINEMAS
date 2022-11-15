package site.servlet;

import java.io.IOException;

import DAO.FavoriteDAO;
import DAO.UserDAO;
import DAO.VideoDAO;
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
import model.Video;

@WebServlet("/MovieSingle")
public class MovieSingleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy thông tin User và VideoID
		String username = SessionUtils.getLoginUsername(req);
		String videoid = req.getParameter("id");

		// Nếu User = null thì hiển thị thông tin Video
		if (username == null) {
			showInfoVideo(username, videoid, req, resp);
			PageInfo.prepareAndForwardSite(req, resp, PageType.SITE_MOVIE_SINGLE);
			return;
		}

		// Nếu User != null thì hiển thị thông tin Video và User
		// Và tạo 1 dữ liệu ảo trong Favorites
		try {
			showInfoUser(req, resp);
			showInfoVideo(username, videoid, req, resp);
			creatFavByUser(username, videoid, req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(req, resp, PageType.SITE_MOVIE_SINGLE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void showInfoVideo(String username, String videoID, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (videoID == null) {
			req.setAttribute("error", "Video id is required!");
			return;
		}
		try {

			VideoDAO dao = new VideoDAO();
			Video video = dao.findById(videoID);

			req.setAttribute("videoImg", video.getPoster());
			req.setAttribute("videoTrailer", video.getVideoID());
			req.setAttribute("videoTitle", video.getTitle());
			req.setAttribute("videoViews", video.getViews());
			req.setAttribute("videoDescription", video.getDescription());
			req.setAttribute("btnLike", false);
			if (username != null) {
				FavoriteDAO daocheck = new FavoriteDAO();
				Favorite checkBtnLike = daocheck.findByUserIDAndVideoID(username, videoID);
				req.setAttribute("btnLike", checkBtnLike.getIsLiked());
			}

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error:" + e.getMessage());
		}
	}

	protected void showInfoUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = SessionUtils.getLoginUsername(req);
		UserDAO dao = new UserDAO();

		User user = dao.findById(username);
		if (user.getAdmin()) {
			req.setAttribute("adminRole", user);
		}
		req.setAttribute("user", user);

	}

	protected void creatFavByUser(String username, String videoID, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Kiểm tra xem đối tượng Favorite ảo đã tồn tại hay chưa

		FavoriteDAO favdao = new FavoriteDAO();
		Favorite favoriteCheck = favdao.findByUserIDAndVideoID(username, videoID);

//		//Nếu chưa thì tạo một đối tượng Favorite ảo
		if (favoriteCheck == null) {
			try {
				UserDAO Userdao = new UserDAO();
				User user = Userdao.findById(username);

				VideoDAO dao = new VideoDAO();
				Video video = dao.findById(videoID);
				// Thêm dữ liệu ảo vào Favorites
				Favorite fav = new Favorite();
				fav.setUser(user);
				fav.setVideo(video);
				FavoriteDAO Favdao = new FavoriteDAO();
				Favdao.insert(fav);

				// Hiển thị trạng thái nút Like
				req.setAttribute("btnLike", fav.getIsLiked());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
