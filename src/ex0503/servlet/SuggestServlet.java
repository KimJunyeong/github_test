package ex0503.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class SuggestServlet
 */
@WebServlet("/suggestServlet")
public class SuggestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String [] words = {"자바", "자바 스터디", "자바스크립트", "자바 개발자", "Ajax",
			"웹 프로그래밍", "jsp", "servlet", "json", "javascript", "jQuery", "자카르타"};
	
	/**
	 * 첫 문자가 동일한 단어를 찾아서 List에 담아 리턴해주는 메소드 작성
	 */
	private List<String> search(String keyWord){
		List<String> list = new ArrayList<>();
		//words의 길이만큼 반복문을 돈다. 첫 문자 체크
		for(String word:words) {
			if(word.toLowerCase().startsWith(keyWord.toLowerCase()))
				list.add(word);
		}
		return list;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); //post방식 한글 인코딩 설정
		response.setContentType("text/html;charset=UTF-8");
		
		String keyWord = request.getParameter("keyWord");
		
		List<String> list = search(keyWord);
		
		//front로 전송하기 위해 text형식으로 변환
		//개수|단어, 단어, 단어... 형식
		//String data = list.size()+"|";
		
		PrintWriter out = response.getWriter();
		
		/*
		out.print(list.size()+"|");
		
		for(int i=0; i<list.size();i++) {
			if(i==(list.size()-1))
				out.print(list.get(i));
			else
				out.print(list.get(i)+",");
		}
		*/
		
		//list를 json으로 변환해서 front로 보내기 [{}, {}, {}]
		JSONArray jsonArray = JSONArray.fromObject(list);
		out.println(jsonArray);
	}

}
