
package Repository.Answer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {
    public static void main(String[] args) {
        try {
            AnswerDTO answer = new AnswerDTO("AR1", "nhoc_ddd", "", new Timestamp(System.currentTimeMillis()), true);
            AnswerDAO.addAnswer(answer);
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
