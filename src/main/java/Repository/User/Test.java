package Repository.User;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Test {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
      Timestamp newTime = new Timestamp(-60*100*1000*100);
        System.out.println(newTime);
     //  UserDTO user = new UserDTO("starci", "", "123456", "", "",
        //     "", "admin", null, "", "", "", true);
     //  UserDAO.updateUser("starci", user);
//
    //    System.out.println(UserDAO.getUserUsername("starci").getBalance());
    }
}
