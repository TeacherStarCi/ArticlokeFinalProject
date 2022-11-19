package Controllers.Workspace;

import Repository.Article.ArticleDAO;
import Repository.Article.ArticleDTO;
import Repository.Paper.PaperDAO;
import Repository.Paper.PaperDTO;
import Repository.User.UserDAO;
import Repository.User.UserDTO;
import Utils.Utils;
import jakarta.servlet.RequestDispatcher;
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

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadPublishedPaperServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "StartPublisherWorkspaceServlet";

        try {
            String action = request.getParameter("action");

            Part part = request.getPart("filePDF");
            Part partDemo = request.getPart("demoFilePDF");
            String fileName = Utils.extractFileName(part);
            String fileDemoName = Utils.extractFileName(partDemo);
            String ID = request.getParameter("ID");

            // Test Values
            PaperDTO paper = null;
            //
            try {
                paper = PaperDAO.getPaperID(ID);
            } catch (SQLException | ClassNotFoundException ex) {

            }
            // refines the fileName in case it is an absolute path

            fileName = new File(fileName).getName();
            fileDemoName = new File(fileDemoName).getName();

            if (PaperErrorCatcher.checkFileName(fileName) != null
                    || PaperErrorCatcher.checkFileName(fileDemoName) != null) {
                request.setAttribute("pictureError", PaperErrorCatcher.checkFileName(fileName));
                request.setAttribute("pictureDemoError", PaperErrorCatcher.checkFileName(fileDemoName));
                URL = "PublishPaper.jsp";

            } else {
                String fileDirection = Utils.getFolderFileUpload("Official", request.getServletContext()).getAbsolutePath() + File.separator + fileName;
                part.write(fileDirection);

                String fileDemoDirection = Utils.getFolderFileUpload("Official", request.getServletContext()).getAbsolutePath() + File.separator + fileDemoName;
                partDemo.write(fileDemoDirection);

                Timestamp publishedDate = new Timestamp(System.currentTimeMillis());
                HttpSession session = request.getSession(false);
                UserDTO user = null;
                if (session != null) {
                    String username = paper.getUsername();
                    try {
                        user = UserDAO.getUserUsername(username);
                    } catch (SQLException | ClassNotFoundException ex) {
                    }
                }

                paper.setPublishedStatus("Published");
                ArticleDTO article = new ArticleDTO(ID, paper.getPicture(), paper.getTitle(), user.getUsername(), paper.getTopic(), paper.getDescription(), "Asset/File/Official/" + fileName, "Asset/File/Official/" + fileDemoName, publishedDate, "Private", 0, 0, 0, true);

                try {
                    PaperDAO.updatePaper(paper);
                } catch (SQLException | ClassNotFoundException ex) {
                }
                try {
                    ArticleDAO.addArticle(article);
                } catch (SQLException | ClassNotFoundException ex) {
                }

            }
            String thisO = request.getParameter("thisO");
            request.setAttribute("thisO", thisO);
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
