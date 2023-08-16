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
import java.util.ArrayList;

@WebServlet(name = "SearchFruitServlet",urlPatterns = "/SearchFruitServlet")
public class SearchFruitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        AdminService adminService = new AdminService();
        String number = null;
        String name = null;
        String price = null;
        String unit = null;
        try{
            String cond = request.getParameter("cond");
            if(cond.equals("1")){
                number = request.getParameter("keywords");
            }
            if(cond.equals("2")){
                name = request.getParameter("keywords");
            }
            if(cond.equals("3")){
                price = request.getParameter("keywords");
            }
            if(cond.equals("4")){
                unit = request.getParameter("keywords");
            }
            ArrayList<FruitItem> fruitlist = adminService.queryDataForCon(number,name,price,unit);
            request.setAttribute("fruitlist",fruitlist);
            request.getRequestDispatcher("list.jsp").forward(request,response);
        }catch (Exception e){
            PrintWriter out = response.getWriter();
            out.println("<h2>"+e+"<h2>");
            out.println("返回登录页面重新登录");
            out.println("<a href=ListFruitServlet>浏览水果</a><br>");
            out.println("</body></html>");
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
