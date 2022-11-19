package Controllers.Search;

import Repository.Article.ArticleDAO;
import Repository.Article.ArticleDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class GetResultServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "Search.jsp";
        try {
            String keyword = request.getParameter("keyword");
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String topic = request.getParameter("topic");
            String sort = request.getParameter("sort");
            Date date = null;
            try {
                 date = Date.valueOf(request.getParameter("date"));
            } catch (IllegalArgumentException iae) {
            }
            request.setAttribute("keyword", keyword);
            request.setAttribute("title", title);
            request.setAttribute("author", author);
            request.setAttribute("topic", topic);
            request.setAttribute("sort", sort);
            request.setAttribute("keyword", keyword);
            request.setAttribute("date", date);
            List<ArticleDTO> articles = null;
            try {
                articles = ArticleDAO.getArticlesSearch(keyword, title, author, topic, sort, date);
            } catch (SQLException | ClassNotFoundException ex) {
            }
            request.setAttribute("articles", articles);
            request.setAttribute("dispatchFromController", true);
            
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
