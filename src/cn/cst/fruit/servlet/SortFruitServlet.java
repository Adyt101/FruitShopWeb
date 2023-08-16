package cn.cst.fruit.servlet;

import cn.cst.fruit.bean.FruitItem;
import cn.cst.fruit.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SortFruitServlet",urlPatterns = "/SortFruitServlet")
public class SortFruitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        AdminService adminService = new AdminService();
        String sort = request.getParameter("sort");
        ArrayList<FruitItem> fruitlist = adminService.queryAllDataOrderBy(sort);
        request.setAttribute("fruitlist",fruitlist);
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
