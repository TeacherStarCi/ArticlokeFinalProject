
package Repository.Transaction;

import java.io.Serializable;
import java.sql.Timestamp;

public class TransactionDTO implements Serializable{
   private String ID;
   private String usernameSender;
   private String usernameReceiver;
   private float amount;
   private Timestamp sentDate;  
   private boolean status;

    public TransactionDTO() {
    }

    public TransactionDTO(String ID, String usernameSender, String usernameReceiver, float amount, Timestamp sentDate, boolean status) {
        this.ID = ID;
        this.usernameSender = usernameSender;
        this.usernameReceiver = usernameReceiver;
        this.amount = amount;
        this.sentDate = sentDate;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsernameSender() {
        return usernameSender;
    }

    public void setUsernameSender(String usernameSender) {
        this.usernameSender = usernameSender;
    }

    public String getUsernameReceiver() {
        return usernameReceiver;
    }

    public void setUsernameReceiver(String usernameReceiver) {
        this.usernameReceiver = usernameReceiver;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Timestamp getSentDate() {
        return sentDate;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sentDate = sentDate;
    }
   
}
