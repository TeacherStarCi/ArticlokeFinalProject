/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.AdminCore;

import Controllers.Authentication.ErrorMessage;
import Repository.User.UserDAO;
import Repository.User.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateThisServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "CreateNewAccount.jsp";
          try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String role = request.getParameter("role");
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
           
                   
        } else {
                
            
            
            UserDTO user = new UserDTO(username, null, password, firstName, lastName, null, 0, role, null, null, null, null, true);
            try {
                UserDAO.addUser(user);
            } catch (SQLException | ClassNotFoundException ex) {
            }
            URL = "AdminStartServlet";
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
