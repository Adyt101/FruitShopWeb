package cn.cst.fruit.servlet;

import cn.cst.fruit.tools.JDBCUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from user where username=? and password =?";
        String name = request.getParameter("name").trim();
        String password = request.getParameter("password").trim();
        try{
            conn =(Connection) JDBCUtils.getConnection();
            pst =(PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,password);
            rs = pst.executeQuery();
            if(rs.next()){
                RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
                dispatcher.forward(request,response);
            }else{
                String msg = "您输入的用户名和密码错误，请验证后重新输入";
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<h2>"+name+"登陆反馈<br>"+msg+"<h2>");
                out.println("返回登录页面重新登录");
                out.println("<a href=index.jsp>登录首页</a>");
            }
        }catch (Exception e){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<h2>"+name+"登陆反馈<br>"+e+"<h2>");
            out.println("返回登录页面重新登录");
            out.println("<a href=index.jsp>登录首页</a>");
            out.close();
        }finally {
            JDBCUtils.release(rs,pst,conn);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
