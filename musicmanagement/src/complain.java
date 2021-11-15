

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class complain
 */
@WebServlet("/complain")
public class complain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public complain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String date = request.getParameter("date");
		String username = request.getParameter("username");
		String complain = request.getParameter("complain");
		complaint complaint = new complaint (date,username,complain);
		complaintDao CDao = new complaintDao();
		boolean result = CDao.insert(complaint);
		response.getWriter().print(result);
		if(result==false) {
		response.sendRedirect("music-list.jsp");
		}
		else {
			response.getWriter().print("Try again");
			
		}
		
	}
		
		
			
		
		
	}


