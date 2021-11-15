package com.xadmin.musicmanagement.web;
import com.xadmin.musicmanagement.dao.MusicDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.xadmin.musicmanagement.bean.Music;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class MusicServlet
 */
@WebServlet("/")
public class MusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private MusicDao MusicDao;
	
	public void init() {
		MusicDao = new MusicDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertMusic(request, response);
				break;
			case "/delete":
				deleteMusic(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateMusic(request, response);
				break;
			default:
				listMusic(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listMusic(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Music> listMusic = MusicDao.selectAllMusics();
		request.setAttribute("listMusic", listMusic);
		RequestDispatcher dispatcher = request.getRequestDispatcher("music-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("music-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Music existingMusic = MusicDao.selectMusic(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("music-form.jsp");
		request.setAttribute("music", existingMusic);
		dispatcher.forward(request, response);

	}

	private void insertMusic(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String title = request.getParameter("title");
		String singers = request.getParameter("singers");
		String Country = request.getParameter("country");
		Music newMusic = new Music (title,singers, Country);
		MusicDao.insertMusic(newMusic);
		response.sendRedirect("list");
	}

	private void updateMusic(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String singers = request.getParameter("singers");
		String Country = request.getParameter("country");

		Music book = new Music(id, title,singers,Country);
		MusicDao.updateMusic(book);
		response.sendRedirect("list");
	}

	private void deleteMusic(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		MusicDao.deleteMusic(id);
		response.sendRedirect("list");

	}

}