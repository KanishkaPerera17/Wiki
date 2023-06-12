package mvcpackage.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcpackage.model.bean.Article;
import mvcpackage.model.bean.Category;
import mvcpackage.model.dao.CategoryDAO;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryDAO categoryDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
        super();
    }
    
	public void init() {
		categoryDAO = new CategoryDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "No action";
		}
		System.out.print(action);
		try {
			switch (action) {
			case "auth_categories":
				Categories(request, response);
				break;
			case "auth_list":
				listCurrentCategories(request, response);
				break;
			case "new":
				newCategory(request, response);
				break;
			case "delete":
				deleteExistingCategory(request, response);
				break;
			default:
				listAllCategories(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	// All the categories
	private void listAllCategories(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		List<Category> allCategories = categoryDAO.selectAllCategries();
		request.setAttribute("listALLCategories", allCategories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("category_page.jsp");
		dispatcher.forward(request, response);
	}
	
	// Administration Function
	private void Categories(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		List<Category> allCategories = categoryDAO.selectAllCategries();
		request.setAttribute("listALLCategories", allCategories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_console/create_and_update_article.jsp");
		dispatcher.forward(request, response);
	}
	
	// Current categories
	private void listCurrentCategories(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		List<Category> allCategories = categoryDAO.selectAllCategries();
		request.setAttribute("listALLCategories", allCategories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_console/new_category_create.jsp");
		dispatcher.forward(request, response);
	}

	private void newCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Category category = new Category(name, description);
		categoryDAO.newCategory(category);
		response.sendRedirect(request.getContextPath() + "/CategoryController?action=auth_list");
	}
	
	// Delete Category By ID
	private void deleteExistingCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		categoryDAO.deleteCategory(id);
	    response.sendRedirect(request.getContextPath() + "/CategoryController?action=auth_list");
	}

}
