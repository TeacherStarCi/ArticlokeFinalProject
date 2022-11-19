package Controllers.ArticleCRUD;

import Repository.Article.ArticleDAO;
import Repository.Article.ArticleDTO;
import Repository.Reaction.ReactionDAO;
import Repository.User.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewArticleServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "ViewArticle.jsp";
        try {
            String ID = request.getParameter("ID");
            ArticleDTO article = null;
            try {
                article = ArticleDAO.getArticle(ID);
            } catch (SQLException | ClassNotFoundException ex) {
            }
            request.setAttribute("article", article);
            HttpSession session = request.getSession(false);
            UserDTO user = null;
            String username = null;
            if (session != null) {
                user = (UserDTO) session.getAttribute("user");
                if (user != null) {
                    username = user.getUsername();
                }
            }
            boolean check = false;
            try {
                check
                        = ReactionDAO.checkReaction(ID, username);
            } catch (ClassNotFoundException | SQLException ex) {
            }
            if (check){
                request.setAttribute("stayUnlove", true);
            } else {
                 request.setAttribute("stayLove", true);
            }
            if (article.getUsername().equals(username)) {
                request.setAttribute("thisIsMyArticle", true);
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
