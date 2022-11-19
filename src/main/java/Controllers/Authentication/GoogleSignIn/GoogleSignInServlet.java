package Controllers.Authentication.GoogleSignIn;

import Repository.Download.DownloadDAO;
import Repository.Reaction.ReactionDAO;
import Repository.User.UserDAO;
import Repository.User.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class GoogleSignInServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "SessionSetServlet";
        try {
            if (request.getSession(false).getAttribute("user") == null) {
                String code = request.getParameter("code");
                String accessToken = GoogleSupport.getToken(code);
                GoogleDTO userToken = GoogleSupport.getUserInfo(accessToken);
                String username = userToken.getId();
                
                UserDTO user = null;    
       
                try {             
                    user = UserDAO.getUserUsername(username);
                } catch (SQLException | ClassNotFoundException ex) {
                }
                if (user == null){
                String email = userToken.getEmail();
                String firstName = userToken.getGiven_name();
                String lastName = userToken.getFamily_name();
                String picture = userToken.getPicture();

                user = new UserDTO(username, email, null,
                        firstName, lastName, picture,0, "User", null, null, null, null, true);
                try {
                    UserDAO.addUser(user);
                } catch (SQLException | ClassNotFoundException ex) {
                }
                }
 
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
               
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
