package Controllers.UserCRUD;

import Controllers.Authentication.ErrorMessage;
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
import java.sql.Date;
import java.sql.SQLException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UpdateProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "MyProfile.jsp";
        try {
            Part part = request.getPart("picture");
            String fileName = Utils.extractFileName(part);
            fileName = new File(fileName).getName();

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            Date DOB = null;
            try {
                DOB = Date.valueOf(request.getParameter("DOB"));
            } catch (IllegalArgumentException iae) {
            }
            String phoneNumber = request.getParameter("phoneNumber");
            String organization = request.getParameter("organization");
            HttpSession session = request.getSession(false);
            UserDTO user = null;
            if (session != null) {
                user = (UserDTO) session.getAttribute("user");
            }
            if (!fileName.equals("")) {
                part.write(Utils.getFolderUpload("User", request.getServletContext()).getAbsolutePath() + File.separator + fileName);
                user.setPicture("Asset/Image/User/"+ fileName);
            }
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAddress(address);
            user.setDOB(DOB);
            user.setOrganization(organization);
            user.setPhoneNumber(phoneNumber);
            try {
                UserDAO.updateUser(user.getUsername(), user);
            } catch (SQLException | ClassNotFoundException ex) {
            }
            session.setAttribute("user", user);

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(URL);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
