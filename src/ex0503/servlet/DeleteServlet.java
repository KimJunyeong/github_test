package ex0503.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ex0503.dao.CustomerDAO;
import ex0503.dao.CustomerDAOImpl;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		System.out.println("asdf    "+id);
		CustomerDAO customerDAO = new CustomerDAOImpl();
		int result = customerDAO.delete(id);
		System.out.println("qwerty : "+result);
		PrintWriter out = response.getWriter();
		out.println(result+"");
	}

}
