package cn.cst.fruit.servlet;

import cn.cst.fruit.bean.FruitItem;
import cn.cst.fruit.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddFruitServlet",urlPatterns = "/AddFruitServlet")
public class AddFruitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        AdminService adminService = new AdminService();
        try{
            String number = request.getParameter("number");
            String name = request.getParameter("name");
            String price =request.getParameter("price");
            String unit = request.getParameter("unit");
            FruitItem fruitItem = new FruitItem(number,name,price,unit);
            if(adminService.addFruitItem(fruitItem)){
                request.getRequestDispatcher("ListFruitServlet").forward(request,response);
            }else {
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h2>"+"水果编号不能重复，请重新输入！"+"</h2><br>");
                out.println("<a href=add.jsp>添加水果</a><br>");
                out.println("<a href=ListFruitServlet>浏览水果</a><br>");
                out.println("</body></html>");
                out.close();
            }
        }catch (Exception e){

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
