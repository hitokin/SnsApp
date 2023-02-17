package login_servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountRegistDAO;
import dao.LoginDAO;
import dao.SelectDAO;
import dto.Account;
import dto.Post;
import util.GenerateHashedPw;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("UTF-8");
		
		String userMail = request.getParameter("userMail");
		String password = request.getParameter("password");
		String salt = AccountRegistDAO.getSalt(userMail);
		
		if(salt == null) {
			String view =".?/error=1";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
			return;
		}
		//ぱすわーどをハッシュ
		String hashedPw = GenerateHashedPw.getSafetyPassword(password, salt);
		//一致するアカウントを検索
		System.out.println("ハッシュ：" + hashedPw);
		System.out.println("メール：" + userMail);
		Account account = LoginDAO.login(userMail, hashedPw);
		
		ArrayList<Post> myRecordList = SelectDAO.selectPost();
		System.out.println(myRecordList);
		if(account == null) {
			String view ="./?error=1";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			//自分の投稿がなかった場合（nullだった場合）
			if(myRecordList.isEmpty()) {
				session.setAttribute("recordFlg", 1);
				session.setAttribute("registFlg", 1);
			} else {
				session.setAttribute("recordFlg", 0);
				session.setAttribute("registFlg", 0);
				//投稿が存在する場合は投稿の内容リストと件数を引き渡す
				session.setAttribute("myRecord", myRecordList);
				session.setAttribute("myRecordSize", myRecordList.size());
			}
			session.setAttribute("userName", account.getUserName());
			session.setAttribute("userId", account.getUserId());
			String view = "WEB-INF/home/home.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
