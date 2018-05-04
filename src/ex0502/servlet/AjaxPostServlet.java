package ex0502.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxPostServlet
 */
@WebServlet("/postServlet")
public class AjaxPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//book ��ȣ�� �ش��ϴ� �̹��� �غ�
		String [] imgNames = {"spring.jpg", "android.jpg", "jquery.jpg", "jsmasterbook.jpg", "jpup_id.jpg"};
		
		//���۵� book ������ ���� �̹����� ã�Ƽ� �����ش�
		String book = request.getParameter("book"); //0,1,2,3,4
		PrintWriter out = response.getWriter();
		out.println(imgNames[Integer.parseInt(book)]);
	}

}
