package admin.servlet;

import java.io.IOException;
import java.util.List;

import DAO.FavoriteDAO;
import DAO.ShareDAO;
import DAO.VideoDAO;
import common.PageInfo;
import common.PageType;
import domain.FavoriteReport;
import domain.FavoriteUserReport;
import domain.ShareReport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Video;

@WebServlet({ "/ReportManage", "/ReportManage/tab1", "/ReportManage/tab2", "/ReportManage/tab3" })
public class ReportManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("showtab1", "show active");
		req.setAttribute("activetab1", "active");
		req.setAttribute("tabselectedtab1", "true");

		reportByVideos(req, resp);
		reportUsersByVideos(req, resp);
		reportShareByVideos(req, resp);
		PageInfo.prepareAndForward(req, resp, PageType.REPORT_MANAGEMENT_PAGE);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();

		if (url.contains("tab1")) {
			reportByVideos(req, resp);
			req.setAttribute("showtab1", "show active");
			req.setAttribute("activetab1", "active");
			req.setAttribute("tabselectedtab1", "true");
		} else if (url.contains("tab2")) {
			reportUsersByVideos(req, resp);
			req.setAttribute("showtab2", "show active");
			req.setAttribute("activetab2", "active");
			req.setAttribute("tabselectedtab2", "true");
		} else if (url.contains("tab3")) {
			reportShareByVideos(req, resp);
			req.setAttribute("showtab3", "show active");
			req.setAttribute("activetab3", "active");
			req.setAttribute("tabselectedtab3", "true");
		}
		PageInfo.prepareAndForward(req, resp, PageType.REPORT_MANAGEMENT_PAGE);

	}

	protected void reportByVideos(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			FavoriteDAO dao = new FavoriteDAO();
			List<FavoriteReport> list = dao.reportByVideos();
			req.setAttribute("favList", list);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}

	}

	protected void reportUsersByVideos(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {

			String video = req.getParameter("videoIDuser");
			System.out.println(video + " videoID check tab 2");
			VideoDAO vdao = new VideoDAO();
			List<Video> vList = vdao.findAll();

			if (video == null && vList.size() > 0) {
				video = vList.get(0).getVideoID();
			}
			
			Video vTitle = vdao.findById(video);

			FavoriteDAO dao = new FavoriteDAO();
			List<FavoriteUserReport> list = dao.reportUsersByVideos(video);

			req.setAttribute("videoSelected", vTitle.getTitle());
			req.setAttribute("videoUserID", video);
			req.setAttribute("vidList", vList);
			req.setAttribute("favUsers", list);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());

		}

	}

	protected void reportShareByVideos(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String videoID = req.getParameter("videoIDshare");
			VideoDAO vdao = new VideoDAO();
			List<Video> vList = vdao.findAll();

			if (videoID == null && vList.size() > 0) {
				videoID = vList.get(0).getVideoID();
			}
			Video vTitle = vdao.findById(videoID);

			ShareDAO dao = new ShareDAO();
			List<ShareReport> list = dao.reportShareByVideos(videoID);

			req.setAttribute("videoSelected", vTitle.getTitle());
			req.setAttribute("vidList", vList);
			req.setAttribute("shareList", list);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}

	}

}
