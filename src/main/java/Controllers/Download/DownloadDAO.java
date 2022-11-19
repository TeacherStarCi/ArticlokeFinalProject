
package Controllers.Download;

import Utils.DatabaseConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DownloadDAO implements Serializable{
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
}
