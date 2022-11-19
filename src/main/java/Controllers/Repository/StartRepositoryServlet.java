
package Controllers.Repository;

import Repository.Article.ArticleDAO;
import Repository.Article.ArticleDTO;
import Repository.Paper.PaperDAO;
import Repository.Paper.PaperDTO;
import Repository.User.UserDTO;
import Utils.DateDTO;
import Utils.Utils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartRepositoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "MyRepository.jsp";
        try{
            List<ArticleDTO> articles = new ArrayList<>();
            List<PaperDTO> pendingPapers = new ArrayList<>();
            List<PaperDTO> approvedPapers = new ArrayList<>();
            List<PaperDTO> draftPapers = new ArrayList<>();
            HttpSession session = request.getSession(false);
            String username = null;
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute("user");
                if (user != null) {
                    username = user.getUsername();
                }
            }

            try {
                articles = ArticleDAO.getArticlesKeywordTopic(username, "", "All");
            } catch (SQLException | ClassNotFoundException ex) {
            }
            request.setAttribute("articles", articles);
            try {
                pendingPapers = PaperDAO.getPapersKeywordTopic(username,"", "All", "Pending");
            } catch (SQLException | ClassNotFoundException ex) {
            }
            Map<String, DateDTO> correspondingPendingTimes = new HashMap<>();
            for (PaperDTO paper : pendingPapers) {
                String ID = paper.getID();
                boolean advanced = paper.isAdvanced();
                Timestamp submittedDate = paper.getSubmittedDate();
                DateDTO remaining = Utils.getRemainingTime(submittedDate, advanced);
                correspondingPendingTimes.put(ID, remaining);
            }
            request.setAttribute("correspondingPendingTimes", correspondingPendingTimes);  
            
            
            
            request.setAttribute("pendingPapers", pendingPapers);
            try {
                approvedPapers = PaperDAO.getPapersKeywordTopic(username, "", "All", "Approved");
            } catch (SQLException | ClassNotFoundException ex) {
            }
            
            Map<String, DateDTO> correspondingApprovingTimes = new HashMap<>();
            for (PaperDTO paper :  approvedPapers) {
                String ID = paper.getID();
                boolean advanced = paper.isAdvanced();
                Timestamp submittedDate = paper.getSubmittedDate();
                DateDTO remaining = Utils.getRemainingTimeForPublisher(submittedDate, advanced);
                correspondingApprovingTimes.put(ID, remaining);
            }
            request.setAttribute("correspondingApprovingTimes", correspondingApprovingTimes);  
            
            request.setAttribute("approvedPapers", approvedPapers);
            try {
                draftPapers = PaperDAO.getPapersKeywordTopic(username, "", "All", "Draft");
            } catch (SQLException | ClassNotFoundException ex) {
            }
            request.setAttribute("draftPapers", draftPapers);
        }finally{
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
