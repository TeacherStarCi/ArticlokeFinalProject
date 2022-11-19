
package Repository.Download;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DownloadDTO download = new DownloadDTO("AR1","starci", new Timestamp(System.currentTimeMillis()));
        System.out.println( DownloadDAO.checkDownload("AR1", "starci")); 
             
    }
}
