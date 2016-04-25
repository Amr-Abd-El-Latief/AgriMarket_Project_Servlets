/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.pojo.Order;
import model.pojo.Product;
import model.pojo.ProductPerOrder;
import model.pojo.User;

/**
 *
 * @author Israa
 */
@WebServlet(name = "Temp", urlPatterns = {"/Temp"})
public class Temp extends HttpServlet {

    ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        this.config = config;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        config.getServletContext().setAttribute("interests", request);
        User user = null;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            user = new User();
            user.setEmail("israa@yahoo.com");
            user.setUserName("IsraaKilany");
            user.setCreditNumber("1234567891234567");
            user.setPassword("123");
            user.setDOB(LocalDate.now().minusYears(20));
            user.setAddress("addreesrr;dp;gk,");
            user.setJob("Farmer");
            ArrayList<String> interests = new ArrayList<>();
            interests.add("Tomatoes");
            interests.add("Rice");
            user.setInterests(interests);
            ArrayList<Order> orders = new ArrayList<Order>();
            Order cart = new Order();
            cart.setId(1);
            cart.setUser_email("israa@yahoo.com");
            cart.setDate(LocalDate.now());
            cart.setStatus("cart");
            ArrayList<Product> products = new ArrayList<>();
            Product product = new Product();
            product.setImage(user.getImage());
            product.setQuantity(20);
            product.setPrice(34.0f);
            product.setName("Tomato");
            product.setDesc("Tomato 7elwa :D");
//            product.set
            products.add(product);
            ArrayList<ProductPerOrder> quantities=new ArrayList<>();
            quantities.add(new ProductPerOrder(product, 10));
            
            cart.setItems(quantities);

            cart.setProducts(products);
            orders.add(cart);
            user.setOrders(orders);

            session = request.getSession(true);
            session.setAttribute("user", user);

        }

        response.sendRedirect("edit_profile.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
