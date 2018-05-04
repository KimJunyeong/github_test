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
import ex0503.dto.CustomerDTO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CustomerDTO customerDTO = new CustomerDTO(request.getParameter("id"),
										request.getParameter("name"),
										Integer.parseInt(request.getParameter("age")),
										request.getParameter("tel"),
										request.getParameter("addr"));
		CustomerDAO customerDAO = new CustomerDAOImpl();
		int result = customerDAO.update(customerDTO);
		
		PrintWriter out = response.getWriter();
		out.println(result+"");
	}

}
