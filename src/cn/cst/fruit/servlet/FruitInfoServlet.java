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

@WebServlet(name = "FruitInfoServlet",urlPatterns = "/FruitInfoServlet")
public class FruitInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        AdminService adminService = new AdminService();
        String number = request.getParameter("number");
        FruitItem fruitItem = adminService.findFruitByNumber(number);
        if(fruitItem==null){
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>"+"水果编号不存在，找不到水果"+"</h2><br>");
            out.println("<a href=ListFruitServlet>浏览水果</a><br>");
            out.println("</body></html>");
            out.close();out.println();
        }else{
            request.setAttribute("fruitItem",fruitItem);
            request.getRequestDispatcher("updata.jsp").forward(request,response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
