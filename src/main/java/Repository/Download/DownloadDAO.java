package Repository.Download;

import Utils.DatabaseConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DownloadDAO implements Serializable {

    public static int getTotalDownload(String username) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT COUNT(do.ID) as totalDownload FROM\n"
                + "Download do inner join Paper pp ON do.ID = pp.ID\n"
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
                    result = res.getInt("totalDownload");

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

    public static boolean checkDownload(String ID, String username)
            throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM Download "
                + "WHERE ID = ? and username = ?";
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
                while (res.next()) {
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

    public static boolean addDownload(DownloadDTO download)
            throws SQLException, ClassNotFoundException {
        String SQL = "INSERT INTO Download(ID, username, downloadedDate)"
                + " VALUES(?,?,?)";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, download.getID());
                pre.setString(2, download.getUsername());
                pre.setTimestamp(3, download.getDownloadedDate());

                int affectedRow = pre.executeUpdate();
                if (affectedRow > 0) {
                    flag = true;
                }

            }
        } catch (SQLException e) {
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
