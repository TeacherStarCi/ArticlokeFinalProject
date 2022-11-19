
package Repository.Feedback;

import java.io.Serializable;

import java.sql.Timestamp;

public class FeedbackDTO implements Serializable{
    private String ID;
    private String username;
    private String header;
    private String content;
    private Timestamp feedbackDate;
    private boolean status;

    public FeedbackDTO() {
    }

    public FeedbackDTO(String ID, String username, String header, String content, Timestamp feedbackDate, boolean status) {
        this.ID = ID;
        this.username = username;
        this.header = header;
        this.content = content;
        this.feedbackDate = feedbackDate;
        this.status = status;
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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Timestamp feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
