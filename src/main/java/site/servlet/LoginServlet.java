package site.servlet;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import DAO.UserDAO;
import common.CookieUtils;
import common.PageInfo;
import common.PageType;
import common.SessionUtils;
import domain.LoginForm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = CookieUtils.get("username", req);

		if (username == null) {
			
			PageInfo.prepareAndForwardSite(req, resp, PageType.SITE_HOME_PAGE);
		
			return;
		}
		SessionUtils.add(req, "username", username);
		
		req.getRequestDispatcher("Homepage").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			LoginForm form = new LoginForm();
			BeanUtils.populate(form, req.getParameterMap());

			UserDAO dao = new UserDAO();
			User user = dao.findById(form.getUsername());

			if (user != null && user.getPassword().equals(form.getPassword())) {
				SessionUtils.add(req, "username", user.getUsername());

				if (form.isRemember()) {
					CookieUtils.add("username", form.getUsername(), 1, resp);
				} else {
					CookieUtils.add("username", form.getUsername(), 0, resp);
				}
				
				if (user.getAdmin()) {
					req.setAttribute("adminRole", user);
				}
				req.setAttribute("isLogin", true);
				req.setAttribute("user", user);
				
				req.getRequestDispatcher("Homepage").forward(req, resp);

				return;
			}
			req.setAttribute("error", "Invalid username or password");
			

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
			
		}
		req.getRequestDispatcher("Homepage").forward(req, resp);
	}
	
}
