package ex0504.upload.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/uploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//업로드 된 경우이므로 multipartRequest를 사용해야 함
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String saveDir=request.getServletContext().getRealPath("/save");
		int maxSize=1024*1024*100;
		String encoding="UTF-8";
		MultipartRequest m = 
				new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
		
		//맵을 JSON으로 변환하기
		String name = m.getParameter("name");
		String subject = m.getParameter("subject");
		String fileName = m.getFilesystemName("file");
		long fileSize=m.getFile("file").length();
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("name", name);
		map.put("subject", subject);
		map.put("fileName", fileName);
		map.put("fileSize", fileSize);
		
		JSONArray arr = JSONArray.fromObject(map);
		
		PrintWriter out = response.getWriter();
		out.println(arr);
	}

}
