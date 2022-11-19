package Controllers.Workspace;

import Repository.Paper.PaperDAO;
import Repository.Paper.PaperDTO;
import Utils.DateDTO;
import Utils.Utils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartEditorWorkspaceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "EditorWorkspace.jsp";
        try {
            List<PaperDTO> pendingPapers = null;
            try {
                pendingPapers = PaperDAO.getPapersPublishedStatus("Pending");
            } catch (SQLException | ClassNotFoundException ex) {
            }
            request.setAttribute("pendingPapers", pendingPapers);
            Map<String, DateDTO> correspondingTimes = new HashMap<>();
            for (PaperDTO paper : pendingPapers) {
                String ID = paper.getID();
                boolean advanced = paper.isAdvanced();
                Timestamp submittedDate = paper.getSubmittedDate();
                DateDTO remaining = Utils.getRemainingTime(submittedDate, advanced);
                correspondingTimes.put(ID, remaining);
            }
            request.setAttribute("correspondingTimes", correspondingTimes);  
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
