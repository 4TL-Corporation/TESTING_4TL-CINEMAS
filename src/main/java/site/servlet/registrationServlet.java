package site.servlet;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import DAO.UserDAO;
import common.EmailUtils;
import common.PageInfo;
import common.PageType;
import domain.Email;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;


@WebServlet("/registration")
public class registrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PageInfo.prepareAndForwardSite(req, resp, PageType.SITE_HOME_PAGE);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		try {

			BeanUtils.populate(user, req.getParameterMap());
			user.setEmail(req.getParameter("emailSignin"));
			UserDAO dao = new UserDAO();
			
			dao.insert(user);

			req.setAttribute("message", "Success !!");
			sendMailSuccess(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}

		PageInfo.prepareAndForward(req, resp, PageType.SITE_HOME_PAGE);
	}
	
	protected void sendMailSuccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String emailAddress = req.getParameter("emailSignin");
			String username = req.getParameter("username");
			
			UserDAO dao = new UserDAO();
			User user = dao.findByUsernameAndEmail(username, emailAddress);
			
			if(user == null) {
				req.setAttribute("error", "Something wrong in your account registration process. Please try again !!");
			}else {
				Email email = new Email();
				email.setFrom("4tlixcompany@gmail.com");
				email.setFromPassword("gnlhymcmwtiaazxr");
				email.setTo(emailAddress);
				email.setSubject("Successful account registration");
				StringBuilder sb = new StringBuilder();
				sb.append("Dear ").append(username).append("<br>");
				sb.append("Congratulations on becoming a member of 4TLIX. <br>");
				sb.append("Experience the top quality movies at 4TLIX. <br>");
				sb.append("Regards<br>");
				sb.append("Administrator");
				
				email.setContent(sb.toString());
				EmailUtils.send(email);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("error", e.getMessage());
		}
		
	}

}
