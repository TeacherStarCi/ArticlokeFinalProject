package Repository.Paper;

import Repository.Article.ArticleDTO;
import Utils.DatabaseConnector;
import Utils.DateDTO;
import Utils.Utils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PaperDAO implements Serializable {

    private static List<String> getIDs() throws SQLException, ClassNotFoundException {
        String SQL = "SELECT ID FROM Paper";

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
            return "AR001";
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
        nextID = "AR" + count;
        return nextID;

    }

    public static List<PaperDTO> getPapersUsernameLastedModifiedDate(String username_) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM Paper WHERE username = ? ORDER BY modifiedDate DESC";

        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        List<PaperDTO> papers = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, username_);
                res = pre.executeQuery();
                while (res.next()) {
                    String ID = res.getString("ID");
                    String title = res.getString("title");
                    String picture = res.getString("picture");
                    String topic = res.getString("topic");
                    String description = res.getString("description");
                    String content = res.getString("content");
                    Timestamp createdDate = res.getTimestamp("createdDate");
                    Timestamp modifiedDate = res.getTimestamp("modifiedDate");
                    String username = res.getString("username");
                    String publishedStatus = res.getString("publishedStatus");
                    boolean advanced = res.getBoolean("advanced");
                    Timestamp submittedDate = res.getTimestamp("submittedDate");
                    boolean status = res.getBoolean("status");

                    PaperDTO paper = new PaperDTO(ID, title, picture, topic, description, content, createdDate, modifiedDate, username, publishedStatus, advanced, submittedDate, status);
                    if (papers == null) {
                        papers = new ArrayList<>();
                    }
                    papers.add(paper);
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
        return papers;
    }

    public static List<PaperDTO> getPapersPublishedStatus(String publishedStatus) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM Paper WHERE publishedStatus = ?";
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        List<PaperDTO> papers = new ArrayList<>();
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, publishedStatus);
                res = pre.executeQuery();
                while (res.next()) {
                    String ID = res.getString("ID");
                    String title = res.getString("title");
                    String picture = res.getString("picture");
                    String topic = res.getString("topic");
                    String description = res.getString("description");
                    String content = res.getString("content");
                    Timestamp createdDate = res.getTimestamp("createdDate");
                    Timestamp modifiedDate = res.getTimestamp("modifiedDate");
                    String username = res.getString("username");
                    boolean advanced = res.getBoolean("advanced");
                    Timestamp submittedDate = res.getTimestamp("submittedDate");
                    boolean status = res.getBoolean("status");

                    PaperDTO paper = new PaperDTO(ID, title, picture, topic, description, content, createdDate, modifiedDate, username, publishedStatus, advanced, submittedDate, status);
    
                    papers.add(paper);
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
        return papers;

    }

    public static PaperDTO getPaperID(String ID) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM Paper WHERE ID = ?";
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        PaperDTO paper = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, ID);
                res = pre.executeQuery();
                while (res.next()) {
                    String title = res.getString("title");
                    String picture = res.getString("picture");
                    String topic = res.getString("topic");
                    String description = res.getString("description");
                    String content = res.getString("content");
                    Timestamp createdDate = res.getTimestamp("createdDate");
                    Timestamp modifiedDate = res.getTimestamp("modifiedDate");
                    String username = res.getString("username");
                    String publishedStatus = res.getString("publishedStatus");
                    boolean advanced = res.getBoolean("advanced");
                    Timestamp submittedDate = res.getTimestamp("submittedDate");
                    boolean status = res.getBoolean("status");

                    paper = new PaperDTO(ID, title, picture, topic, description, content, createdDate, modifiedDate, username, publishedStatus, advanced, submittedDate, status);

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
        return paper;

    }

    public static List<PaperDTO> getPapersKeywordTopic(String username_, String keyword, String topic_, String publishedStatus) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM Paper\n"
                + "WHERE username = ? AND topic = ? AND publishedStatus = ? AND\n"
                + "(ID like ? OR Description like ?\n"
                + "OR title like ?) ";

        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        List<PaperDTO> papers = new ArrayList<>();
        try {

            con = DatabaseConnector.makeConnection();
            if (con != null) {

                if (!topic_.equals("All")) {

                    pre = con.prepareStatement(SQL);
                    pre.setString(1, username_);
                    pre.setString(2, topic_);
                    pre.setString(3, publishedStatus);
                    pre.setString(4, "%" + keyword + "%");
                    pre.setString(5, "%" + keyword + "%");
                    pre.setString(6, "%" + keyword + "%");

                } else {
                    SQL = "SELECT * FROM Paper\n"
                            + "WHERE username = ? AND publishedStatus = ? AND\n"
                            + "(ID like ? OR Description like ?\n"
                            + "OR title like ?) ";
                    pre = con.prepareStatement(SQL);
                    pre.setString(1, username_);
                    pre.setString(2, publishedStatus);
                    pre.setString(3, "%" + keyword + "%");
                    pre.setString(4, "%" + keyword + "%");
                    pre.setString(5, "%" + keyword + "%");
                }
                res = pre.executeQuery();
                while (res.next()) {
                    String ID = res.getString("ID");
                    String title = res.getString("title");
                    String picture = res.getString("picture");
                    String topic = res.getString("topic");
                    String description = res.getString("description");
                    String content = res.getString("content");
                    Timestamp createdDate = res.getTimestamp("createdDate");
                    Timestamp modifiedDate = res.getTimestamp("modifiedDate");
                    String username = res.getString("username");
                    boolean advanced = res.getBoolean("advanced");
                    Timestamp submittedDate = res.getTimestamp("submittedDate");
                    boolean status = res.getBoolean("status");

                    PaperDTO paper = new PaperDTO(ID, title, picture, topic, description, content, createdDate, modifiedDate, username, publishedStatus, advanced, submittedDate, status);

                    papers.add(paper);
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
        return papers;

    }

    public static List<PaperDTO> papersKeywordFilter(String keyword, String publishedStatus) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM Paper\n"
                + "WHERE (username like ? or title like ? or description like ? or content like ?)"
                + " AND publishedStatus = ?";

        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        List<PaperDTO> papers = new ArrayList<>();
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, "%" + keyword + "%");
                pre.setString(2, "%" + keyword + "%");
                pre.setString(3, "%" + keyword + "%");
                pre.setString(4, "%" + keyword + "%");
                pre.setString(5, publishedStatus);
                res = pre.executeQuery();
                while (res.next()) {
                    String ID = res.getString("ID");
                    String title = res.getString("title");
                    String picture = res.getString("picture");
                    String topic = res.getString("topic");
                    String description = res.getString("description");
                    String content = res.getString("content");
                    Timestamp createdDate = res.getTimestamp("createdDate");
                    Timestamp modifiedDate = res.getTimestamp("modifiedDate");
                    String username = res.getString("username");
                    boolean advanced = res.getBoolean("advanced");
                    Timestamp submittedDate = res.getTimestamp("submittedDate");
                    boolean status = res.getBoolean("status");
                    PaperDTO paper = new PaperDTO(ID, title, picture, topic, description, content, createdDate, modifiedDate, username, publishedStatus, advanced, submittedDate, status);
                    papers.add(paper);
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
        return papers;

    }

    public static List<PaperDTO> papersTopicFilter(String topic, List<PaperDTO> papers) {
        List<PaperDTO> returnList = new ArrayList<>();
        if (!topic.equals("All")) {
            for (PaperDTO paper : papers) {
                if (paper.getTopic().equals(topic)) {
                    returnList.add(paper);
                }
            }
        } else {
            returnList = papers;
        }
        return returnList;
    }

    public static List<PaperDTO> papersDisplayFilter(String display, List<PaperDTO> papers) {

        List<PaperDTO> returnList = new ArrayList<>();
        if (display.equals("Expired")) {
            for (PaperDTO paper : papers) {
                DateDTO date = Utils.getRemainingTime(paper.getSubmittedDate(), paper.isAdvanced());
                if (date == null) {
                    returnList.add(paper);
                }

            }

        } else if (display.equals("Closing Date")) {
            for (PaperDTO paper : papers) {
              DateDTO date = Utils.getRemainingTime(paper.getSubmittedDate(), paper.isAdvanced());
                if (date != null && date.getDate() < 1) {             
                    returnList.add(paper);
                }
            }
                Collections.sort(returnList, new Comparator<PaperDTO>() {
                    @Override
                    public int compare(PaperDTO a1, PaperDTO a2) {
                        if (a1.getSubmittedDate().compareTo(a2.getSubmittedDate()) > 0) {
                            return 1;
                        } else {
                            if (a1.getSubmittedDate().equals(a2.getSubmittedDate())) {
                                return 0;
                            } else {
                                return -1;
                            }
                        }
                    }
                });
            

        } else if (display.equals("Latest")) {
            returnList = new ArrayList<>();
            for (PaperDTO paper : papers) {
                long milis = System.currentTimeMillis() - paper.getSubmittedDate().getTime();
                if (milis >= 0) {
                    DateDTO date = Utils.getDateFromMillis(milis);
                    if (date.getDate() < 1) {
                    returnList.add(paper);
                    }
                }
            }
            Collections.sort(returnList, new Comparator<PaperDTO>() {
                @Override
                public int compare(PaperDTO a1, PaperDTO a2) {
                    if (a1.getSubmittedDate().compareTo(a2.getSubmittedDate()) < 0) {
                        return 1;
                    } else {
                        if (a1.getSubmittedDate().equals(a2.getSubmittedDate())) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                }
            });
        } else {
            returnList = papers;
        }
        return returnList;
    }
  public static List<PaperDTO> papersAdvancedFilter(boolean advanced, List<PaperDTO> papers) {
        List<PaperDTO> returnList = new ArrayList<>();
        if (advanced) {
            for (PaperDTO paper : papers) {
                if (paper.isAdvanced()) {
                    returnList.add(paper);
                }
            }
        } else {
            returnList = papers;
        }
        return returnList;
    }
    public static List<PaperDTO> searchPaperWorkspace(String keyword, String publishedStatus, String display, String topic, boolean advanced) throws SQLException, ClassNotFoundException {
        List<PaperDTO> list = papersKeywordFilter(keyword, publishedStatus);
        List<PaperDTO> list2 = papersTopicFilter(topic, list);
        List<PaperDTO> list3 = papersAdvancedFilter(advanced, list2);
        return papersDisplayFilter(display, list3);
    }

    public static boolean addPaper(PaperDTO paper)
            throws SQLException, ClassNotFoundException {
        String SQL = "INSERT INTO Paper(ID, title, picture, topic, description, content, createdDate, modifiedDate, username, publishedStatus, advanced, submittedDate , status)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, paper.getID());
                pre.setString(2, paper.getTitle());
                pre.setString(3, paper.getPicture());
                pre.setString(4, paper.getTopic());
                pre.setString(5, paper.getDescription());
                pre.setString(6, paper.getContent());
                pre.setTimestamp(7, paper.getCreatedDate());
                pre.setTimestamp(8, paper.getModifiedDate());
                pre.setString(9, paper.getUsername());
                pre.setString(10, paper.getPublishedStatus());
                pre.setBoolean(11, paper.isAdvanced());
                pre.setTimestamp(12, paper.getSubmittedDate());
                pre.setBoolean(13, paper.isStatus());
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

    public static boolean updatePaper(PaperDTO paper)
            throws SQLException, ClassNotFoundException {

        String SQL = "UPDATE Paper SET title = ?, picture = ?, topic = ?, description = ?,"
                + " content = ?, createdDate = ?, modifiedDate = ?, username = ?, publishedStatus = ?, advanced = ?, submittedDate = ?, status = ? "
                + " WHERE ID = ?";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);

                pre.setString(1, paper.getTitle());
                pre.setString(2, paper.getPicture());
                pre.setString(3, paper.getTopic());
                pre.setString(4, paper.getDescription());
                pre.setString(5, paper.getContent());
                pre.setTimestamp(6, paper.getCreatedDate());
                pre.setTimestamp(7, paper.getModifiedDate());
                pre.setString(8, paper.getUsername());
                pre.setString(9, paper.getPublishedStatus());
                pre.setBoolean(10, paper.isAdvanced());
                pre.setTimestamp(11, paper.getSubmittedDate());
                pre.setBoolean(12, paper.isStatus());
                pre.setString(13, paper.getID());
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
