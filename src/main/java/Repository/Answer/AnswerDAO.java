package Repository.Answer;

import Utils.DatabaseConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerDAO implements Serializable {

    public static boolean addAnswer(AnswerDTO answer) throws SQLException, ClassNotFoundException {
        String SQL = "INSERT INTO Answer(ID, username, answer, answerDate, answerStatus)"
                + " VALUES(?,?,?,?,?)";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, answer.getID());
                pre.setString(2, answer.getUsername());
                pre.setString(3, answer.getAnswer());
                pre.setTimestamp(4, answer.getAnswerDate());
                pre.setBoolean(5, answer.isAnswerStatus());
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
