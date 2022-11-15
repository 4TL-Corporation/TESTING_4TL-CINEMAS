package common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;






public class PageInfo {
	public static Map<PageType, PageInfo> pageRoute = new HashMap<>();

	static {
		//ADMIN PAGE
		pageRoute.put(PageType.USER_MANAGEMENT_PAGE, new PageInfo("User Management", "/admin/users/users.jsp", null));
		pageRoute.put(PageType.REPORT_MANAGEMENT_PAGE, new PageInfo("Report Management", "/admin/reports/reports.jsp", null));
		pageRoute.put(PageType.VIDEO_MANAGEMENT_PAGE, new PageInfo("Video Management", "/admin/videos/videos.jsp", null));
		
		//USER PAGE
		pageRoute.put(PageType.SITE_HOME_PAGE, new PageInfo("Home Page", "./layout_home/body-home.jsp", null));
		pageRoute.put(PageType.SITE_USER_PROFILE, new PageInfo("User Profile", "./layout_home/body-profile.jsp", null));
		pageRoute.put(PageType.SITE_USER_FAVORITE, new PageInfo("User Favorite", "./layout_favorite/body-favorite.jsp", null));
		pageRoute.put(PageType.SITE_MOVIE_SINGLE, new PageInfo("Movie Single", "./layout_singleMovie/body-movieSingle.jsp", null));
		pageRoute.put(PageType.SITE_FORGOT_PASSWORD, new PageInfo("Forgot Password", "./layout_forgotPassword/body-forgotPassword.jsp", null));
		pageRoute.put(PageType.SITE_SHARE_PAGE, new PageInfo("Share Page", "./layout_share/share.jsp", null));

	}

	public static void prepareAndForward(HttpServletRequest req, HttpServletResponse resp, PageType pageTyge)
			throws ServletException, IOException {
		PageInfo page = pageRoute.get(pageTyge);

		req.setAttribute("page", page);
		
		req.getRequestDispatcher("/admin/layout.jsp").forward(req, resp);
	}
	
	public static void prepareAndForwardSite(HttpServletRequest req, HttpServletResponse resp, PageType pageTyge)
			throws ServletException, IOException {
		PageInfo page = pageRoute.get(pageTyge);

		req.setAttribute("page", page);
		
		req.getRequestDispatcher("/user/index.jsp").forward(req, resp);
	}

	private String title;
	private String contentUrl;
	private String scripUrl;
	
	
	public PageInfo(String title, String contentUrl, String scripUrl) {
		super();
		this.title = title;
		this.contentUrl = contentUrl;
		this.scripUrl = scripUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public String getScripUrl() {
		return scripUrl;
	}
	public void setScripUrl(String scripUrl) {
		this.scripUrl = scripUrl;
	}

	

}
