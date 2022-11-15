package site.servlet;

import java.io.IOException;
import java.util.List;

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
import model.User;
import model.Video;

@WebServlet({"/Homepage"})
public class HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = SessionUtils.getLoginUsername(req);
		//Check status User
		if (username == null) {
			//If the user hasn't logged in
			//Then show info web
			findAll(req, resp);
			ranDomTrailer(req, resp);
			req.getRequestURI().toString();
			PageInfo.prepareAndForwardSite(req, resp, PageType.SITE_HOME_PAGE);
			
			return;
		}
	
		try {
			//If the user has logged in
			// show info user on nav-menu
			showInfoUser(req, resp);
			
			// show items movies
			findAll(req, resp);
			ranDomTrailer(req, resp);
			
			PageInfo.prepareAndForwardSite(req, resp, PageType.SITE_HOME_PAGE);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			VideoDAO dao = new VideoDAO();

			List<Video> list = dao.findAll();

			req.setAttribute("videos", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error:" + e.getMessage());
		}
	}
	
	protected void ranDomTrailer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			VideoDAO dao = new VideoDAO();

			List<Video> list = dao.findRandom3Videos();
			
			req.setAttribute("randomVD", list);
			
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
}
