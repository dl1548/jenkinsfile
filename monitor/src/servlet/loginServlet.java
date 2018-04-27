package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zabbix.api.test.TestTrigger;

import dao.userDao;

@WebServlet(name="/loginServlet")  
public class loginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public loginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	 public void doPost(HttpServletRequest request, HttpServletResponse response)  
	            throws ServletException, IOException {  
	        request.setCharacterEncoding("utf-8");  
	        response.setContentType("text/html;charset=utf-8");  
	        String name = request.getParameter("name");  
	        String password = request.getParameter("password"); 
	       // System.out.println(name);
	       // System.out.println(password);
	        String psw =new userDao().findUsername(name);  
	        if(psw ==null){  
	            request.setAttribute("msg", "用户名错误");  
	            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);  
	            return;  
	        }  
	        if(psw!=null&&!psw.equals(password)){  
	            request.setAttribute("msgp", "密码错误");  
	            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);  
	            return;  
	        }  
	        if(psw.equals(password)){  
	            request.setAttribute("msg", "用户："+name+",欢迎访问");  
	            request.setAttribute("triggerList",getTriggerInfo());
	            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);  
	            //    response.setHeader("Refresh","1;url=index.jsp");  
	        }  
	          
	    }  

	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
   private List getTriggerInfo(){
	   TestTrigger tt=new TestTrigger();
		return tt.testGet();
   }
}
