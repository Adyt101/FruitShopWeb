package cn.cst.fruit.servlet;

import cn.cst.fruit.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelFruitServlet",urlPatterns = "/DelFruitServlet")
public class DelFruitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminService adminService = new AdminService();
        String number = request.getParameter("number");
        String[] numbers = number.split(",");
        for(String id:numbers){
            adminService.deleteFruitById(id);
        }
        request.getRequestDispatcher("ListFruitServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
