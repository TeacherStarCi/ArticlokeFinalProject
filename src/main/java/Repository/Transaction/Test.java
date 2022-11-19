
package Repository.Transaction;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        String ID = TransactionDAO.getNextID();
        System.out.println(ID);
      // TransactionDTO trans = new TransactionDTO(ID, "starci", "starciDA", 0, new Timestamp(System.currentTimeMillis()));
      // TransactionDAO.addTransaction(trans);
        System.out.println(TransactionDAO.getTransSender("starci").size());
    }
}
