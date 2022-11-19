package Repository.Answer;

import java.io.Serializable;
import java.sql.Timestamp;

public class AnswerDTO implements Serializable {

    private String ID;
    private String username;
    private String answer;
    private Timestamp answerDate;
    private boolean answerStatus;

    public AnswerDTO() {
    }

    public AnswerDTO(String ID, String username, String answer, Timestamp answerDate, boolean answerStatus) {
        this.ID = ID;
        this.username = username;
        this.answer = answer;
        this.answerDate = answerDate;
        this.answerStatus = answerStatus;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Timestamp getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Timestamp answerDate) {
        this.answerDate = answerDate;
    }

    public boolean isAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(boolean answerStatus) {
        this.answerStatus = answerStatus;
    }

}
