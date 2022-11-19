package Repository.Notification;

import Repository.Article.ArticleDTO;
import Utils.DatabaseConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO implements Serializable {

    private static List<String> getIDs() throws SQLException, ClassNotFoundException {
        String SQL = "SELECT ID FROM Notification";

        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        List<String> IDs = new ArrayList<>();
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                res = pre.executeQuery();
                while (res.next()) {
                    String ID = res.getString("ID");
                    IDs.add(ID);
                    IDs.add(ID);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return IDs;
    }

    public static String getNextID() throws SQLException, ClassNotFoundException {
        String nextID;
        List<String> IDs = getIDs();
        if (IDs.isEmpty()) {
            return "NT1";
        }
        List<Integer> numbers = new ArrayList<>();
        for (String ID : IDs) {
            int number = Integer.parseInt(ID.substring(2));
            numbers.add(number);
        }
        int count = 1;
        boolean allowContinue;
        do {
            allowContinue = false;
            for (int number : numbers) {
                if (count == number) {
                    count++;
                    allowContinue = true;
                }
            }

        } while (allowContinue);
        nextID = "NT" + count;
        return nextID;

    }

    public static List<NotificationDTO> getNoti(String username) throws SQLException, ClassNotFoundException {
        List<NotificationDTO> notis = new ArrayList<>();
        String SQL = "SELECT * FROM Notification WHERE username = ? AND\n"
                + "status = 1 ORDER BY createdDate DESC";
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {

                pre = con.prepareStatement(SQL);
                pre.setString(1, username);
                res = pre.executeQuery();

                while (res.next()) {
                    String ID = res.getString("ID");
                    String header = res.getString("header");
                    String content = res.getString("content");
                    Timestamp createdDate = res.getTimestamp("createdDate");
                    boolean status = res.getBoolean("status");
                    NotificationDTO noti = new NotificationDTO(ID, username, header, content, createdDate, status);
                    notis.add(noti);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return notis;
    }

    public static boolean addNoti(NotificationDTO noti) throws SQLException, ClassNotFoundException {
        String SQL = "INSERT INTO Notification(ID, username, header, content, createdDate, status "
                + ")"
                + " VALUES(?,?,?,?,?,?)";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, noti.getID());
                pre.setString(2, noti.getUsername());
                pre.setString(3, noti.getHeader());
                pre.setString(4, noti.getContent());
                pre.setTimestamp(5, noti.getCreatedDate());
                pre.setBoolean(6, noti.isStatus());

                int affectedRow = pre.executeUpdate();
                if (affectedRow > 0) {
                    flag = true;
                }

            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return flag;
    }

    public static boolean removeNotis(String username) throws SQLException, ClassNotFoundException {
        String SQL = "UPDATE Notification\n"
                + "SET status = 0 WHERE username = ?";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, username);
                int affectedRow = pre.executeUpdate();
                if (affectedRow > 0) {
                    flag = true;
                }

            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return flag;
    }
}
