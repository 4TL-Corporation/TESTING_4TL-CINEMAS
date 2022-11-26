package admin.servlet;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import DAO.VideoDAO;
import common.PageInfo;
import common.PageType;
import common.UploadUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Video;

@WebServlet({ "/VideosManagementServlet", "/VideosManagementServlet/insert", "/VideosManagementServlet/update",
		"/VideosManagementServlet/delete", "/VideosManagementServlet/reset", "/VideosManagementServlet/edit" })
@MultipartConfig
public class VideosManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		
		if (uri.contains("delete")) {
			delete(req, resp);
			return;
		} else if (uri.contains("edit")) {
			edit(req, resp);
			return;
		} else if (uri.contains("reset")) {
			reset(req, resp);
			return;
		}
		
		
		Video video = new Video();
		findAll(req, resp);
		video.setPoster("404-poster.jpg");
		req.setAttribute("video", video);

		PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		
		if (uri.contains("insert")) {
			insert(req, resp);
			return;
		} else if (uri.contains("delete")) {
			delete(req, resp);
			return;
		} else if (uri.contains("update")) {
			update(req, resp);
			return;
		} else if (uri.contains("reset")) {
			reset(req, resp);
			return;
		}

	}

	private void reset(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Video video = new Video();
		
		video.setPoster("404-poster.jpg");
		req.setAttribute("video", video);
		findAll(req, resp);
		PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);
		
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

	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Video video = new Video();
		try {

			BeanUtils.populate(video, req.getParameterMap());

			video.setPoster(UploadUtils.processUploadField("cover", req, "/uploads", video.getPoster()));

			VideoDAO dao = new VideoDAO();
			dao.insert(video);

			req.setAttribute("video", video);

			req.setAttribute("message", "Video inserted !!");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
		findAll(req, resp);
		PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");

		if (id == null) {
			req.setAttribute("error", "Video id is required!");
			PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);
			return;
		}
		
		try {

			VideoDAO dao = new VideoDAO();
			Video video = dao.findById(id);
			
			if (video == null) {
				req.setAttribute("error", "Video id is required!");
				PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);
				return;
			}

			dao.delete(id);

			req.setAttribute("message", "Video deleted !!");
			req.setAttribute("video", new Video());
			video.setPoster("404-poster.jpg");
			findAll(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Video video = new Video();
		try {

			BeanUtils.populate(video, req.getParameterMap());

			VideoDAO dao = new VideoDAO();
			Video oldvideo = dao.findById(video.getVideoID());
			
			if (req.getPart("cover").getSize()==0) {
				video.setPoster(oldvideo.getPoster());
			}else {
				video.setPoster("uploads/" + UploadUtils.processUploadField("cover", req, 
						"/uploads", video.getPoster()));
			}
			
			dao.update(video);

			req.setAttribute("video", video);
			req.setAttribute("message", "Video updated !!");
			findAll(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);

	}

	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id == null) {
			req.setAttribute("error", "Video id is required!");
			PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);
			return;
		}
		try {

			VideoDAO dao = new VideoDAO();
			Video video = dao.findById(id);

			req.setAttribute("video", video);
			findAll(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error:" + e.getMessage());
		}
		PageInfo.prepareAndForward(req, resp, PageType.VIDEO_MANAGEMENT_PAGE);

	}

}
