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
import mvcpackage.model.dao.ArticleDAO;
import mvcpackage.model.dao.CategoryDAO;

/**
 * Servlet implementation class ArticleController
 */
@WebServlet("/ArticleController")
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleDAO articleDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleController() {
        super();
    }
    
	public void init() {
		articleDAO = new ArticleDAO();
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
		try {
			switch (action) {
			case "new":
				InsertNewArticle(request, response);
				break;
			case "more":
				selectArticleById(request, response);
				break;
			case "find":
				selectArticleByTitle(request, response);
				break;
			case "all":
				listAllArticles(request, response);
				break;
			case "auth_all_articles":
				AdminArticles(request, response);
				break;
			case "auth_hidden_articles":
				HidddenArticles(request, response);
				break;
			case "auth_show_hide":
				UpdateShowHide(request, response);
				break;
			case "auth_delete_article":
				deleteExistingArticle(request, response);
				break;
			case "auth_show_edit":
				showEditArticle(request, response);
				break;
			case "auth_update":
				updateExistingArticle(request, response);
				break;
			case "full_articles":
				fullArticleById(request, response);
				break;
			default:
				listRecArticles(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	// Recently uploaded articles
	private void listRecArticles(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		List<Article> recArticles = articleDAO.selectRecArticles();
		request.setAttribute("listRecArticles", recArticles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home_page.jsp");
		dispatcher.forward(request, response);
	}
	
	// All of articles
	private void listAllArticles(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		List<Article> allArticles = articleDAO.selectAllArticles();
		request.setAttribute("listALLArticles", allArticles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("article_page.jsp");
		dispatcher.forward(request, response);
	}
	
	// Search articles by keyword (title)
	private void selectArticleByTitle(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		List<Article> allArticles = articleDAO.selectArticleByTitle(request.getParameter("keyword"));
		request.setAttribute("listALLArticles", allArticles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("article_page.jsp");
		dispatcher.forward(request, response);
	}
	
	// Search article by it's id
	private void selectArticleById(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("find"));
		Article allArticles = articleDAO.selectArticleByID(id);
		request.setAttribute("listALLArticles", allArticles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("article_page.jsp");
		dispatcher.forward(request, response);
	}
	
	// Administration Functions
	// All article list for CRUD operation
	private void AdminArticles(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		List<Article> allArticles = articleDAO.selectAllArticles();
		request.setAttribute("AdminArticles", allArticles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_console/all_articles.jsp");
		dispatcher.forward(request, response);
	}
	
	// Full Article Read
	private void fullArticleById(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Article allArticles = articleDAO.selectArticleByID(id);
		request.setAttribute("article", allArticles);
		RequestDispatcher dispatcher = request.getRequestDispatcher(request.getParameter("path"));
		dispatcher.forward(request, response);
	}
	// Hidden Articles
	private void HidddenArticles(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		List<Article> allArticles = articleDAO.AllHiddenArticles();
		request.setAttribute("HiddenArticles", allArticles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_console/hidden_articles.jsp");
		dispatcher.forward(request, response);
	}
	
	// Insert New Article
	private void InsertNewArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String content = request.getParameter("content");
		Boolean is_public = Boolean.parseBoolean(request.getParameter("is_private")); 
		Article arc = new Article(title, category, content, is_public);
		articleDAO.newArticle(arc);
		response.sendRedirect(request.getContextPath() + "/ArticleController?action=auth_all_articles");
	}
	
	// Update Existing Article
	private void updateExistingArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String content = request.getParameter("content");
		Boolean is_public = Boolean.parseBoolean(request.getParameter("is_private")); 
		Article arc = new Article(id, title, category, content, is_public);
		articleDAO.updateArticle(arc);
		response.sendRedirect(request.getContextPath() + "/ArticleController?action=auth_all_articles");
	}
	
	// Show Hide Articles
	private void UpdateShowHide(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		articleDAO.ShowHideArticle(id);
		response.sendRedirect(request.getContextPath() + "/ArticleController?action=" + request.getParameter("path"));
	}
	
	private void showEditArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> allCategories = categoryDAO.selectAllCategries();
		request.setAttribute("listALLCategories", allCategories);
		
		int id = Integer.parseInt(request.getParameter("id"));
		Article existingArticle = articleDAO.selectArticleByID(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_console/create_and_update_article.jsp");
		request.setAttribute("article", existingArticle);
		dispatcher.forward(request, response);
	}

	// Delete Article By ID
	private void deleteExistingArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		articleDAO.deleteArticle(id);
	    response.sendRedirect(request.getContextPath() + "/ArticleController?action=" + request.getParameter("path"));
	}
}
