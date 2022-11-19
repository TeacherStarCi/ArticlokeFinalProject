package Repository.Feedback;

import Utils.DatabaseConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO implements Serializable {

    private static List<String> getIDs() throws SQLException, ClassNotFoundException {
        String SQL = "SELECT ID FROM Feedback";

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
            return "FB1";
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
        nextID = "FB" + count;
        return nextID;

    }
    
     public static List<FeedbackDTO> getFeedbacks(String username) throws SQLException, ClassNotFoundException {
        List<FeedbackDTO> feedbacks = new ArrayList<>();
        String SQL = "SELECT * FROM Feedback WHERE username = ? AND\n"
                + "status = 1 ORDER BY feedbackDate DESC";
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
                    Timestamp feedbackDate = res.getTimestamp("feedbackDate");
                    boolean status = res.getBoolean("status");
                   FeedbackDTO feedback = new FeedbackDTO(ID, username, header, content, feedbackDate, status);
                   feedbacks.add(feedback);
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
        return feedbacks ;
    }

    public static boolean addFeedback(FeedbackDTO feedback) throws SQLException, ClassNotFoundException {
        String SQL = "INSERT INTO Feedback(ID, username, header, content, feedbackDate, status)"
                + " VALUES(?,?,?,?,?,?)";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, feedback.getID());
                pre.setString(2, feedback.getUsername());
                pre.setString(3, feedback.getHeader());
                pre.setString(4, feedback.getContent());
                pre.setTimestamp(5, feedback.getFeedbackDate());
                pre.setBoolean(6, feedback.isStatus());
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
    
       public static boolean removeFeedbacks(String username) throws SQLException, ClassNotFoundException {
        String SQL = "UPDATE Feedback\n"
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
