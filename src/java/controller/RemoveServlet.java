/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDAO;
import dto.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
@WebServlet(name = "RemoveServlet", urlPatterns = {"/RemoveServlet"})
public class RemoveServlet extends HttpServlet {

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
    String url = "items.jsp";
    String id = request.getParameter("id");
    HttpSession session = request.getSession();

    try {
        List<Product> cart = (List<Product>) session.getAttribute("CART");

        if (cart != null) {
            float total = 0;

            // Xóa sản phẩm từ giỏ hàng
            Iterator<Product> iterator = cart.iterator();
            while (iterator.hasNext()) {
                Product p = iterator.next();
                if (p.getId().equals(id)) {
                    // Kiểm tra quantity, giảm đi 1 nếu lớn hơn 1
                    if (p.getQuantity() > 1) {
                        p.setQuantity(p.getQuantity() - 1);
                    } else {
                        // Nếu quantity là 1, xóa sản phẩm khỏi giỏ hàng
                        iterator.remove();
                    }
                    break;
                }
            }

            // Tính lại tổng giá trị giỏ hàng
            for (Product products : cart) {
                total += (products.getPrice() * products.getQuantity());
            }

            session.setAttribute("CART", cart);
            session.setAttribute("TOTAL", total);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        request.getRequestDispatcher(url).forward(request, response);
    }

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
