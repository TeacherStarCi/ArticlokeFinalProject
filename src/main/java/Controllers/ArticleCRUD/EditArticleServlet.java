package Controllers.ArticleCRUD;

import Repository.Article.ArticleDAO;
import Repository.Article.ArticleDTO;
import Repository.Paper.PaperDAO;
import Repository.Paper.PaperDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditArticleServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "ViewArticleServlet";
        try {
            String ID = request.getParameter("ID");
            ArticleDTO article = null;
            try {
                article = ArticleDAO.getArticle(ID);
            } catch (SQLException | ClassNotFoundException ex) {
            }
            String permission = request.getParameter("permission");
            String changePriceS = request.getParameter("price");
            if (changePriceS.equals("")){
                changePriceS = Float.toString(article.getPrice()) ;
            }
            request.setAttribute("permission", permission);
            float price = -1;
            try {
              float priceChange = Float.parseFloat(changePriceS);
               
                if (priceChange < 0) {
                    throw new NumberFormatException();
                }
                price = priceChange;
            } catch (NumberFormatException  ex) {
                request.setAttribute("validError", true);
            }
           
            if (price != -1) {
                article.setPrice(price);
                request.setAttribute("price", price);
            }
            article.setPermission(permission);
           
            try {
                ArticleDAO.updateArticle(article);
            } catch (SQLException | ClassNotFoundException ex) {
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(URL);
            rd.forward(request, response);
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
