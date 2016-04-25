/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.CategoryDao;
import model.DAOImp.CategoryDaoImp;
import model.pojo.Category;

/**
 *
 * @author muhammad
 */
public class GetGategoriesController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        CategoryDao categoryDaoImp = new CategoryDaoImp();
        System.out.println("gategory");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<Category> categories = categoryDaoImp.getAllCategories();
        out.print(gson.toJson(categories));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String catName = request.getParameter("categoryName");
        CategoryDao categoryDaoImp = new CategoryDaoImp();
        boolean check = categoryDaoImp.addCategory(catName);
        if (check) {
            
            response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + "/AgriMarket/admin/getProducts?success=Successfully#header3-41");
        } else {
            response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + "/AgriMarket/admin/getProducts?status=Exist!#header3-41");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
