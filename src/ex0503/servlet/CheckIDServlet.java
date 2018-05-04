package ex0503.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ex0503.dao.CustomerDAO;
import ex0503.dao.CustomerDAOImpl;
import ex0503.dto.CustomerDTO;

/**
 * Servlet implementation class CheckIDServlet
 */
@WebServlet("/checkIDServlet")
public class CheckIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		CustomerDAO dao = new CustomerDAOImpl();
		String result = dao.idCheck(id);
		System.out.println(result);
		PrintWriter out = response.getWriter();
		out.println(result);
	}

}
