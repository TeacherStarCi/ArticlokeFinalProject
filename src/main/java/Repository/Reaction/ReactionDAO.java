package Repository.Reaction;

import Utils.DatabaseConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReactionDAO implements Serializable {

    public static int getTotalReaction(String username) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT COUNT(re.ID) as totalReaction FROM\n"
                + "Reaction re inner join Paper pp ON re.ID = pp.ID\n"
                + "WHERE pp.username = ?\n"
                + "GROUP BY pp.username";
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        int result = 0;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, username);
                res = pre.executeQuery();
                while (res.next()) {
                    result = res.getInt("totalReaction");

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
        return result;
    }

    public static boolean addReaction(String ID, String username) throws SQLException, ClassNotFoundException {
        String SQL = "INSERT INTO Reaction(ID, username) VALUES(?,?)";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, ID);
                pre.setString(2, username);
                int affectedRow = pre.executeUpdate();
                if (affectedRow > 0) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
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

    public static boolean deleteReaction(String ID, String username) throws SQLException, ClassNotFoundException {
        String SQL = "DELETE FROM Reaction WHERE ID = ? AND username = ?";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, ID);
                pre.setString(2, username);
                int affectedRow = pre.executeUpdate();
                if (affectedRow > 0) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
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

    public static boolean checkReaction(String ID, String username) throws ClassNotFoundException, SQLException {
        String SQL = "SELECT * FROM Reaction WHERE ID = ? AND username = ?";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, ID);
                pre.setString(2, username);
                res = pre.executeQuery();
                if (res.next() == true) {
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
