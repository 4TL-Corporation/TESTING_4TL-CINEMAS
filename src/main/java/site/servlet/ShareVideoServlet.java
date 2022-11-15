package site.servlet;

import java.io.IOException;
import java.util.Date;

import DAO.ShareDAO;
import DAO.UserDAO;
import DAO.VideoDAO;
import common.EmailUtils;
import common.PageInfo;
import common.PageType;
import common.SessionUtils;
import domain.Email;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Share;
import model.User;
import model.Video;

@WebServlet("/ShareVideo")
public class ShareVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (!SessionUtils.isLogin(req)) {
			resp.sendRedirect("Homepage");
			return;
		}
		showInfoUser(req, resp);
		String videoID = req.getParameter("videoID");

		if (videoID == null) {
			resp.sendRedirect("Homepage");
			return;
		}
		
		findVideoById(req, resp);
		req.setAttribute("videoID", videoID);
		PageInfo.prepareAndForwardSite(req, resp, PageType.SITE_SHARE_PAGE);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String emailAddress = req.getParameter("email");
			String videoLink = req.getParameter("videoID");

			if (videoLink == null) {
				req.setAttribute("error", "VideoID is null");
			} else {
				// Tạo đối tượng Email
				Email email = new Email();
				email.setFrom("4tlixcompany@gmail.com");
				email.setFromPassword("gnlhymcmwtiaazxr");
				email.setTo(emailAddress);
				email.setSubject("Share Favorite Video");
				StringBuilder sb = new StringBuilder();
				sb.append("Dear Ms/Mr. <br>");
				sb.append("The video  is more interesting and I want to share with you. <br>");
				sb.append("Please click the link ").append(String.format("<a href='%s'>View Video</a><br>", videoLink));
				sb.append("Regards<br>");
				sb.append("Administrator");

				// Thực hiện gửi
				email.setContent(sb.toString());
				EmailUtils.send(email);

				ShareDAO dao = new ShareDAO();
				Share share = new Share();
				share.setEmails(emailAddress);
				share.setShareDate(new Date());

				String username = SessionUtils.getLoginUsername(req);
				User user = new User();
				user.setUsername(username);

				share.setUser(user);
				Video video = new Video();
				video.setVideoID(videoLink);
				share.setVideo(video);

				dao.insert(share);
				req.setAttribute("message", "Video link has been sent");
				showInfoUser(req, resp);
				findVideoById(req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", e.getMessage());
		}

		PageInfo.prepareAndForwardSite(req, resp, PageType.SITE_SHARE_PAGE);

	}

	protected void findVideoById(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String videoID = req.getParameter("videoID");
		try {
			VideoDAO dao = new VideoDAO();

			Video video = dao.findById(videoID);

			req.setAttribute("posterVid", video.getPoster());
			req.setAttribute("nameVid", video.getTitle());
			req.setAttribute("rateVid", video.getViews());
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
