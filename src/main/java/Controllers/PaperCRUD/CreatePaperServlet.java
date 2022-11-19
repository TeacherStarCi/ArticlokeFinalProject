package Controllers.PaperCRUD;

import Controllers.Workspace.PaperErrorCatcher;
import Repository.Paper.PaperDAO;
import Repository.Paper.PaperDTO;
import Repository.User.UserDAO;
import Repository.User.UserDTO;
import Utils.Utils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CreatePaperServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "StartRepositoryServlet";
        try {

            Part part = request.getPart("picture");
            String fileName = Utils.extractFileName(part);
            String title = request.getParameter("title");
            String topic = request.getParameter("topic");
            String action = request.getParameter("action");
            String description = request.getParameter("description");
            String content = request.getParameter("content");
            request.setAttribute("title", title);
            request.setAttribute("topic", topic);
            request.setAttribute("description", description);
            request.setAttribute("content", content);
            if (PaperErrorCatcher.checkTitleLength(title) != null
                    || PaperErrorCatcher.checkFileName(fileName) != null
                    || PaperErrorCatcher.checkDescriptionLength(description) != null
                    || PaperErrorCatcher.checkContentLength(content) != null) {
                request.setAttribute("titleError", PaperErrorCatcher.checkTitleLength(title));
                request.setAttribute("pictureError", PaperErrorCatcher.checkFileName(fileName));
                request.setAttribute("descriptionError", PaperErrorCatcher.checkDescriptionLength(description));
                request.setAttribute("contentError", PaperErrorCatcher.checkContentLength(content));
                URL = "CreatePaper.jsp";
            } else {
                String ID = null;
                try {
                    ID = PaperDAO.getNextID();
                } catch (SQLException | ClassNotFoundException ex) {
                }
                // refines the fileName in case it is an absolute path
                fileName = new File(fileName).getName();
                part.write(Utils.getFolderUpload("Paper", request.getServletContext()).getAbsolutePath() + File.separator + fileName);
                Timestamp modifiedDate = null;
                String picture = "Asset/Image/Paper/" + fileName;
                Timestamp createdDate = new Timestamp(System.currentTimeMillis());
                HttpSession session = request.getSession(false);
                String username = null;
                UserDTO user = null;
                if (session != null) {
                    user = (UserDTO) session.getAttribute("user");
                    if (user != null) {
                        username = user.getUsername();
                    }
                }
                String publishedStatus = "Draft";
                Timestamp submittedDate = null;
                boolean status = true;
                boolean advanced = false;
                if (action.equals("Standard")) {
                    submittedDate = new Timestamp(System.currentTimeMillis());
                    publishedStatus = "Pending";
                } else if (action.equals("Advanced")) {
                    if (user.getBalance() < 49) {
                        URL = "CreatePaper.jsp";
                        request.setAttribute("balanceError", "your balance is not enough.");
                        request.getRequestDispatcher(URL).forward(request, response);
                        return;
                    } else {
                        submittedDate = new Timestamp(System.currentTimeMillis());
                        publishedStatus = "Pending";
                        advanced = true;
                        user.setBalance(user.getBalance() - 49);
                        try {
                            UserDAO.updateUser(username, user);
                        } catch (SQLException | ClassNotFoundException ex) {

                        }
                    }
                }
                PaperDTO paper = new PaperDTO(ID, title, picture, topic, description, content, createdDate, modifiedDate, username, publishedStatus, advanced, submittedDate, status);
                try {
                    PaperDAO.addPaper(paper);
                } catch (SQLException | ClassNotFoundException ex) {
                }
                ServletContext context = request.getServletContext();
                List<PaperDTO> pendingPapers = null;

                try {
                    pendingPapers = PaperDAO.getPapersPublishedStatus("Pending");
                } catch (SQLException | ClassNotFoundException ex) {
                }
                context.setAttribute("pendingPapers", pendingPapers);

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
