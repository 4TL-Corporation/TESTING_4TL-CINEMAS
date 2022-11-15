package admin.servlet;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import DAO.UserDAO;
import common.PageInfo;
import common.PageType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet({"/UsersManagement", "/UsersManagement/insert", 
	"/UsersManagement/edit", "/UsersManagement/update", "/UsersManagement/delete", "/UsersManagement/reset"})
public class UsersManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		findAll(req, resp);
		String uri = req.getRequestURI();
		if (uri.contains("edit")) {
			edit(req, resp);
			return;
		}
		if (uri.contains("delete")) {
			delete(req, resp);
			return;
		}
		if (uri.contains("reset")) {
			reset(req, resp);
			return;
		}
		PageInfo.prepareAndForward(req, resp, PageType.USER_MANAGEMENT_PAGE);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		System.out.println(uri);
		if (uri.contains("insert")) {
			insert(req, resp);
			return;
		}
		if (uri.contains("delete")) {
			delete(req, resp);
			return;
		}
		if (uri.contains("update")) {
			update(req, resp);
			return;
		}
		if (uri.contains("reset")) {
			reset(req, resp);
			return;
		}
		if (uri.contains("edit")) {
			edit(req, resp);
			return;
		}
	}

	private void reset(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("user", new User());
		findAll(req, resp);
		PageInfo.prepareAndForward(req, resp, PageType.USER_MANAGEMENT_PAGE);
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		try {

			BeanUtils.populate(user, req.getParameterMap());

			UserDAO dao = new UserDAO();

			dao.update(user);

			req.setAttribute("user", user);
			req.setAttribute("message", "User is updated!!!");
			findAll(req, resp);
		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(req, resp, PageType.USER_MANAGEMENT_PAGE);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy thông tin id
		String id = req.getParameter("username");
		// kiểm tra nếu id chưa nhập
		if (id == null) {
			req.setAttribute("error", "User id is required");
			PageInfo.prepareAndForward(req, resp, PageType.USER_MANAGEMENT_PAGE);
			return;
		}
		try {
			UserDAO dao = new UserDAO();
			User user = dao.findById(id);
			// kiểm tra coi có user trong csdl
			if (user == null) {
				req.setAttribute("error", "User id is not found!");
				PageInfo.prepareAndForward(req, resp, PageType.USER_MANAGEMENT_PAGE);
				return;
			}
			dao.delete(id);

			req.setAttribute("message", "User is deleted!!!");
			req.setAttribute("user", new User());// hiện thị trên form rỗng
			// hiển thị tất cả thông tin đã được cập nhật
			findAll(req, resp);
		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(req, resp, PageType.USER_MANAGEMENT_PAGE);
	}

	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("username");
		if (id == null) {
			req.setAttribute("error", "User id is required");
			PageInfo.prepareAndForward(req, resp, PageType.USER_MANAGEMENT_PAGE);
			return;
		}
		try {
			UserDAO dao = new UserDAO();
			User user = dao.findById(id);
			
			req.setAttribute("user", user);
			findAll(req, resp);
		} catch (Exception e) {
			e.printStackTrace();

			req.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(req, resp, PageType.USER_MANAGEMENT_PAGE);
	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		try {

			BeanUtils.populate(user, req.getParameterMap());

			UserDAO dao = new UserDAO();
			dao.insert(user);

			req.setAttribute("user", user);
			req.setAttribute("message", "User is inserted!!!");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
		findAll(req, resp);
		PageInfo.prepareAndForward(req, resp, PageType.USER_MANAGEMENT_PAGE);
	}

	private void findAll(HttpServletRequest req, HttpServletResponse resp) {
		try {
			UserDAO dao = new UserDAO();

			List<User> list = dao.findAll();

			req.setAttribute("users", list);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error: " + e.getMessage());
		}
	}
}
