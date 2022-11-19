
package Repository.Download;

import java.sql.Timestamp;

public class DownloadDTO {
   private String ID;
   private String username;
   private Timestamp downloadedDate;

    public DownloadDTO(String ID, String username, Timestamp downloadedDate) {
        this.ID = ID;
        this.username = username;
        this.downloadedDate = downloadedDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getDownloadedDate() {
        return downloadedDate;
    }

    public void setDownloadedDate(Timestamp downloadedDate) {
        this.downloadedDate = downloadedDate;
    }
   
}
