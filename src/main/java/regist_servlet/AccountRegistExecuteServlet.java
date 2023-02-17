package regist_servlet;

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
import dao.SelectDAO;
import dto.Account;
import dto.Post;

/**
 * Servlet implementation class AccountRegistExecuteServelt
 */
@WebServlet("/AccountRegistExecuteServlet")
public class AccountRegistExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountRegistExecuteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	
	Account account = (Account)session.getAttribute("registAccountData");
	int result = AccountRegistDAO.Regist(account);
	String path = "";
	if(result==1) {
		ArrayList<Post> myRecordList = SelectDAO.selectPost();
		
		session.setAttribute("myRecord", myRecordList);
		session.setAttribute("myRecordSize", myRecordList.size());
		session.setAttribute("registFlg", 1);
		session.setAttribute("recordFlg", 1);
		session.setAttribute("userName", account.getUserName());
		session.setAttribute("userId", account.getUserId());
		path = "WEB-INF/home/home.jsp";
	} else {
		path = "WEB-INF/AccountRegist_view/AccountRegistForm.jsp?error=1";
	}
	RequestDispatcher dispatcher = request.getRequestDispatcher(path);
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
