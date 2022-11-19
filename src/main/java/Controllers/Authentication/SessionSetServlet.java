package Controllers.Authentication;

import Repository.Download.DownloadDAO;
import Repository.Feedback.FeedbackDAO;
import Repository.Feedback.FeedbackDTO;
import Repository.Notification.NotificationDAO;
import Repository.Notification.NotificationDTO;
import Repository.Reaction.ReactionDAO;
import Repository.Transaction.TransactionDAO;
import Repository.Transaction.TransactionDTO;
import Repository.User.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionSetServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "Home.jsp";
        try {

            HttpSession session = request.getSession(false);
            String username = ((UserDTO) session.getAttribute("user")).getUsername();
            int totalDownload = 0;
            int totalReaction = 0;
            List<NotificationDTO> notis = null;
            List<FeedbackDTO> feedbacks = null;
            List<TransactionDTO> transSender = null;
            List<TransactionDTO> transReceiver = null;
            try {
                totalReaction = ReactionDAO.getTotalReaction(username);
            } catch (SQLException | ClassNotFoundException ex) {
            }

            try {
                totalDownload = DownloadDAO.getTotalDownload(username);
            } catch (SQLException | ClassNotFoundException ex) {
            }
            try {
                notis = NotificationDAO.getNoti(username);
            } catch (SQLException | ClassNotFoundException ex) {
            }
            try {
                feedbacks = FeedbackDAO.getFeedbacks(username);
            } catch (SQLException | ClassNotFoundException ex) {
            }
            
            try {
                transSender = TransactionDAO.getTransSender(username);
            } catch (SQLException | ClassNotFoundException ex) {
            
            }
            try {
                transReceiver = TransactionDAO.getTransReceiver(username);
            } catch (SQLException | ClassNotFoundException ex) {
                
            }

            session.setAttribute("totalDownload", totalDownload);
            session.setAttribute("totalReaction", totalReaction);
            session.setAttribute("notis", notis);
            session.setAttribute("feedbacks", feedbacks);
            session.setAttribute("transSender", transSender);
            session.setAttribute("transReceiver", transReceiver);
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
