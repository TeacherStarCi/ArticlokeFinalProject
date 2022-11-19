
package Controllers.Authentication;

import Repository.User.UserDAO;
import Repository.User.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
          String URL = "SignUp.jsp";
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String term = request.getParameter("term");

            request.setAttribute("username", username);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);

            if (ErrorMessage.checkTerm(term) != null) {
             request.setAttribute("termError", ErrorMessage.checkTerm(term));
             
            } else if (ErrorMessage.checkUsernameExist(username) != null) {
                request.setAttribute("usernameError", ErrorMessage.checkUsernameExist(username));
                
            } else if (ErrorMessage.checkUsernameLength(username) != null
                    || ErrorMessage.checkPasswordLength(password) != null
                    || ErrorMessage.checkPasswordConfirm(password, confirm) != null
                    || ErrorMessage.checkFirstNameLength(firstName) != null
                    || ErrorMessage.checkLastNameLength(lastName) != null) {
                request.setAttribute("usernameError", ErrorMessage.checkUsernameLength(username));
                request.setAttribute("passwordError", ErrorMessage.checkPasswordLength(password));
                request.setAttribute("confirmError", ErrorMessage.checkPasswordConfirm(password, confirm));
                request.setAttribute("firstNameError", ErrorMessage.checkFirstNameLength(firstName));
                request.setAttribute("lastNameError", ErrorMessage.checkLastNameLength(lastName));
                
                   
        }
        
        else {
                    UserDTO user = new UserDTO(username, null, password, firstName, lastName, null,0, "User", null, null, null, null, true);
                    try {
                        UserDAO.addUser(user);
                    } catch (SQLException ex) {
                        System.out.println("SQLException - SignUp");
                    } catch (ClassNotFoundException ex) {
                        System.out.println("ClassNotFoundException - SignUp");
                    }
                    Cookie usernameC = new Cookie("username", username);
                    Cookie passwordC = new Cookie("password", password);
                    usernameC.setMaxAge(60 * 5);
                    passwordC.setMaxAge(60 * 5);
                    response.addCookie(usernameC);
                    response.addCookie(passwordC);

                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);

                    URL = "SessionSetServlet";
                
            }
    }

    
        finally {
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
