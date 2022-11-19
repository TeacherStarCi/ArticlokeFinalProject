package Controllers.ArticleCRUD;

import Repository.Article.ArticleDAO;
import Repository.Article.ArticleDTO;
import Repository.Download.DownloadDAO;
import Repository.Download.DownloadDTO;
import Repository.Transaction.TransactionDAO;
import Repository.Transaction.TransactionDTO;
import Repository.User.UserDAO;
import Repository.User.UserDTO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;


public class DownloadArticleServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ID = request.getParameter("ID");
        String username = null;
        UserDTO user = null;
        HttpSession session = request.getSession(false);
        if (session != null) {
            user = (UserDTO) session.getAttribute("user");
        }
        if (user == null) {
            request.setAttribute("notLoginError", true);
            request.getRequestDispatcher("ViewArticleServlet").forward(request, response);

        } else {

            username = user.getUsername();
            boolean bool = false;

            ArticleDTO article = null;
            try {
                article = ArticleDAO.getArticle(ID);
            } catch (SQLException | ClassNotFoundException ex) {
            }
            try {
                bool = !DownloadDAO.checkDownload(ID, username);
            } catch (SQLException | ClassNotFoundException ex) {
            }
            if (bool) {
                float left = user.getBalance() - article.getPrice();
                if (left < 0) {
                    request.setAttribute("notRichError", true);
                    request.getRequestDispatcher("ViewArticleServlet").forward(request, response);
                    return;
                }
                try {
                    DownloadDAO.addDownload(new DownloadDTO(ID, username, new Timestamp(System.currentTimeMillis())));
                } catch (SQLException | ClassNotFoundException ex) {
                }
                user.setBalance(user.getBalance() - article.getPrice());
                try {
                    UserDAO.updateUser(username, user);
                } catch (SQLException | ClassNotFoundException ex) {
                }
                
                UserDTO author = null;
                try {
                    author = UserDAO.getUserUsername(article.getUsername());
                } catch (SQLException | ClassNotFoundException ex) {  
                }
                author.setBalance(author.getBalance() + article.getPrice());
                try {
                    UserDAO.updateUser(author.getUsername(), author);
                } catch (SQLException | ClassNotFoundException ex) {
                }
                
                TransactionDTO trans = null;
                try {
                    trans = new TransactionDTO(TransactionDAO.getNextID(), user.getUsername(), author.getUsername(), article.getPrice() ,new Timestamp(System.currentTimeMillis()),true);
                } catch (SQLException | ClassNotFoundException ex) {
                }
                try {
                    TransactionDAO.addTransaction(trans);
                } catch (SQLException | ClassNotFoundException ex) {
                }
                
            List<TransactionDTO> transSender = null;
            List<TransactionDTO> transReceiver = null;
        
            try {
                transSender = TransactionDAO.getTransSender(username);
            } catch (SQLException | ClassNotFoundException ex) {
            
            }
            try {
                transReceiver = TransactionDAO.getTransReceiver(username);
            } catch (SQLException | ClassNotFoundException ex) {
                
            }

  
            session.setAttribute("transSender", transSender);
            session.setAttribute("transReceiver", transReceiver);
                
                
            }

            String fileLocation = null;
            try {
                fileLocation = ArticleDAO.getArticle(ID).getLink();

            } catch (SQLException | ClassNotFoundException ex) {
            }
            fileLocation = Utils.Utils.conFilePath(fileLocation, request.getServletContext());
            if (fileLocation == null || fileLocation.equals("")) {
                throw new ServletException("File Name can't be null or empty");
            }
            File file = new File(fileLocation);
            if (!file.exists()) {
                throw new ServletException("File doesn't exists on server.");
            }

            ServletContext ctx = getServletContext();
            InputStream fis = new FileInputStream(file);
            String mimeType = ctx.getMimeType(file.getAbsolutePath());
            response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
            response.setContentLength((int) file.length());
            int fileNameIndex = fileLocation.lastIndexOf("\\");
            String fileName = fileLocation.substring(fileNameIndex + 1);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            ServletOutputStream os = response.getOutputStream();
            byte[] bufferData = new byte[1024];
            int read = 0;
            while ((read = fis.read(bufferData)) != -1) {
                os.write(bufferData, 0, read);
            }
            os.flush();
            os.close();
            fis.close();

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
