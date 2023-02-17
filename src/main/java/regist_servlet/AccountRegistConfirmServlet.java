package regist_servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Account;

/**
 * Servlet implementation class AccountRegistServlet
 */
@WebServlet("/AccountRegistConfirmServlet")
public class AccountRegistConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountRegistConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//ユーザ情報の取得
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		String userMail = request.getParameter("userMail");
		String userBirthday = request.getParameter("userBirthday");
		String password = request.getParameter("password");
		//アカウントインスタンスの作成
		Account account = new Account(userName,userId, userMail, userBirthday, password, null);
		
		HttpSession session = request.getSession();
		session.setAttribute("registAccountData", account);
		
		String view = "WEB-INF/AccountRegist_view/AccountRegistConfirm.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
