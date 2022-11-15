package site.servlet;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import DAO.UserDAO;
import common.PageInfo;
import common.PageType;
import common.SessionUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet({ "/EditProfile", "/EditProfile/updatePassword", "/EditProfile/updateProfile" })
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = SessionUtils.getLoginUsername(req);
		if (username == null) {
			req.getRequestDispatcher("/Login").forward(req, resp);
			return;
		}

		try {
			showInfoUser(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForwardSite(req, resp, PageType.SITE_USER_PROFILE);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();

		if (url.contains("updateProfile")) {
			updateProfile(req, resp);
		} else if (url.contains("updatePassword")) {
			updatePassword(req, resp);
		}
		PageInfo.prepareAndForwardSite(req, resp, PageType.SITE_USER_PROFILE);

	}

	protected void updateProfile(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, req.getParameterMap());

			String username = SessionUtils.getLoginUsername(req);
			UserDAO dao = new UserDAO();

			User olduser = dao.findById(username);

			String lastname = req.getParameter("lastname");
			String firstname = req.getParameter("firstname");

			user.setUsername(olduser.getUsername());
			user.setPassword(olduser.getPassword());
			user.setFullname(lastname + " " + firstname);

			dao.update(user);
			req.setAttribute("message", "User-profile updated !!");

			showInfoUser(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}
	}

	protected void updatePassword(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, req.getParameterMap());

			String username = SessionUtils.getLoginUsername(req);
			UserDAO dao = new UserDAO();

			User olduser = dao.findById(username);

			user.setUsername(olduser.getUsername());
			user.setEmail(olduser.getEmail());
			user.setFullname(olduser.getFullname());
			if (req.getParameter("confirmpassword").equals(olduser.getPassword())) {
				//kiem tra trung password
				req.setAttribute("errorPass", "You used this password before !!");
				return;
			} else if (!req.getParameter("newpassword").toString().equals(req.getParameter("confirmpassword").toString())) {
				//kiem tra trung new password vs confirm password
				req.setAttribute("errorPass", "Confirm new password invalid !!");
				return;
			}
			user.setPassword(req.getParameter("confirmpassword"));

			dao.update(user);
			req.setAttribute("messagePass", "Password updated !!");

			showInfoUser(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorPass", e.getMessage());
		}
	}
	
	protected void showInfoUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		String username = SessionUtils.getLoginUsername(req);
		UserDAO dao = new UserDAO();

		User olduser = dao.findById(username);
		
		req.setAttribute("user", olduser);
		user.setUsername(olduser.getUsername());
		user.setFullname(olduser.getFullname());
		
		String fullname = user.getFullname();
		int i = fullname.lastIndexOf(" ");
		if (i > 0) {
			req.setAttribute("lastname", fullname.substring(0, i).trim());
			req.setAttribute("firstname", fullname.substring(i));
		}
		if (user.getAdmin()) {
			req.setAttribute("adminRole", user);
		}
	}
}
