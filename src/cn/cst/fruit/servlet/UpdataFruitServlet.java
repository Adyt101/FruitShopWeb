package cn.cst.fruit.servlet;

import cn.cst.fruit.bean.FruitItem;
import cn.cst.fruit.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdataFruitServlet",urlPatterns = "/UpdataFruitServlet")
public class UpdataFruitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        AdminService adminService = new AdminService();
        try{
            String number = request.getParameter("number");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String unit = request.getParameter("unit");
            FruitItem fruitItem = new FruitItem(number,name,price,unit);
            adminService.updateFruit(fruitItem);
            request.getRequestDispatcher("ListFruitServlet").forward(request,response);
        }catch (Exception e ){
            response.getWriter().println(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
