/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Notification;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author tucuo
 */
public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(NotificationDAO.getNoti("starci"));
        System.out.println(NotificationDAO.getNextID());
        //NotificationDAO.addNoti(new NotificationDTO(NotificationDAO.getNextID(), "starci", "B", "C", new Timestamp(System.currentTimeMillis()), true));
         NotificationDAO.removeNotis("starci");
    }
}
