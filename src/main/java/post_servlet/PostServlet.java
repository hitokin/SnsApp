package post_servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.InsertDAO;
import dao.SelectDAO;
import dto.Post;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("UTF-8");
		//getForm
		String SubmissionDetails = request.getParameter("SubmissionDetails");
		String userId = request.getParameter("userId");
		System.out.println("ユーザID"+userId);
		//投稿内容登録
		if (InsertDAO.SubmissionDdetails(SubmissionDetails,userId) == false) {
			String view = "WEB-INF/home/error.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}

		//自分の投稿内容を全件取得
		ArrayList<Post> RecordList = SelectDAO.selectPost();
		HttpSession session = request.getSession();
		//画面制御フラグをセット
		session.setAttribute("registFlg", 1);
		session.setAttribute("recordFlg", 1);
		if (RecordList != null ) {
			session.setAttribute("registFlg", 0);
			session.setAttribute("recordFlg", 0);
			//自分の投稿内容をセット（件数も同時にセット）
			session.setAttribute("getTimeline", RecordList);
			session.setAttribute("RecordSize", RecordList.size());
		}
		String view = "WEB-INF/home/home.jsp";
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
