package Repository.Transaction;

import Utils.DatabaseConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements Serializable {

    private static List<String> getIDs() throws SQLException, ClassNotFoundException {
        String SQL = "SELECT ID FROM [Transaction]";

        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        List<String> IDs = new ArrayList<>();
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                res = pre.executeQuery();
                while (res.next()) {
                    String ID = res.getString("ID");
                    IDs.add(ID);
                    IDs.add(ID);
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
        return IDs;
    }

    public static String getNextID() throws SQLException, ClassNotFoundException {
        String nextID;
        List<String> IDs = getIDs();
        if (IDs.isEmpty()) {
            return "TS1";
        }
        List<Integer> numbers = new ArrayList<>();
        for (String ID : IDs) {
            int number = Integer.parseInt(ID.substring(2));
            numbers.add(number);
        }
        int count = 1;
        boolean allowContinue;
        do {
            allowContinue = false;
            for (int number : numbers) {
                if (count == number) {
                    count++;
                    allowContinue = true;
                }
            }

        } while (allowContinue);
        nextID = "TS" + count;
        return nextID;

    }

    public static List<TransactionDTO> getTransSender(String sender) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM [Transaction] WHERE usernameSender = ?";
        List<TransactionDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, sender);
                res = pre.executeQuery();
                while (res.next()) {
                    String ID = res.getString("ID");

                    String usernameReceiver = res.getString("usernameReceiver");
                    float amount = res.getFloat("amount");
                    Timestamp sentDate = res.getTimestamp("sentDate");
                    boolean status = res.getBoolean("status");
                    if (status){
                    TransactionDTO trans = new TransactionDTO(ID, sender, usernameReceiver, amount, sentDate, true);
                    list.add(trans);
                     }
                     
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
        return list;
    }

    public static List<TransactionDTO> getTransReceiver(String receiver) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM [Transaction] WHERE usernameReceiver = ?";
        List<TransactionDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, receiver);
                res = pre.executeQuery();
                while (res.next()) {
                    String ID = res.getString("ID");

                    String usernameSender = res.getString("usernameSender");
                    float amount = res.getFloat("amount");
                    Timestamp sentDate = res.getTimestamp("sentDate");
                     boolean status = res.getBoolean("status");
                     if (status){
                    TransactionDTO trans = new TransactionDTO(ID, usernameSender, receiver, amount, sentDate, true);
                    list.add(trans);
                     }
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
        return list;
    }

    public static boolean addTransaction(TransactionDTO trans) throws SQLException, ClassNotFoundException {
        String SQL = "INSERT INTO [Transaction](ID, usernameSender, usernameReceiver, amount, sentDate, status)"
                + " VALUES(?,?,?,?,?,?)";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, trans.getID());
                pre.setString(2, trans.getUsernameSender());
                pre.setString(3, trans.getUsernameReceiver());
                pre.setFloat(4, trans.getAmount());
                pre.setTimestamp(5, trans.getSentDate());
                pre.setBoolean(6, trans.isStatus());
                int affectedRow = pre.executeUpdate();
                if (affectedRow > 0) {
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
