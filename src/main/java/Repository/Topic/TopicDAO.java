package Repository.Topic;

import Utils.DatabaseConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopicDAO implements Serializable {

    public static List<TopicDTO> getTopics() throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM Topic";
        List<TopicDTO> list = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                res = pre.executeQuery();
                while (res.next()) {
                    String topic = res.getString("topic");
                    String picture = res.getString("picture");

                    TopicDTO topicObject = new TopicDTO(topic, picture);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(topicObject);
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

    public static List<TopicPlusArticleCount> getTopicsMostCount()
            throws SQLException, ClassNotFoundException {
        String SQL = "SELECT t.topic, picture, count FROM\n" +
"                (SELECT t.topic, COUNT(a.ID) as count FROM Topic t left join Paper a ON t.topic = a.topic LEFT JOIN Article at ON a.ID = at.ID\n" +
"               GROUP BY t.topic) c inner join Topic t ON c.topic = t.topic \n" +
"              ORDER BY count DESC";
        List<TopicPlusArticleCount> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                res = pre.executeQuery();
                while (res.next()) {
                    String topic = res.getString("topic");
                    String picture = res.getString("picture");
                    int count = res.getInt("count");

                    TopicDTO topicObject = new TopicDTO(topic, picture);
                    TopicPlusArticleCount element = new TopicPlusArticleCount(topicObject, count);

                    list.add(element);
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

    public static Map<String, Integer> getTopicsUsername(String username, String keyword) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT tp.topic, COUNT(tp.topic) as totalTopic FROM Topic tp inner join\n"
                + "                 Paper pp ON tp.topic = pp.topic\n"
                + "                 inner join Article ar ON pp.ID = ar.ID\n"
                + "                 WHERE username = ? AND (title LIKE ? OR ar.ID LIKE ? OR description LIKE ?)\n"
                + "               GROUP BY tp.topic";
        Map<String, Integer> map = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, username);
                pre.setString(2, "%"+keyword+"%");
                pre.setString(3, "%"+keyword+"%");
                pre.setString(4, "%"+keyword+"%");
                res = pre.executeQuery();
                while (res.next()) {
                    String topic = res.getString("topic");
                    int totalTopic = res.getInt("totalTopic");

                    if (map == null) {
                        map = new HashMap<>();
                    }
                    map.put(topic, totalTopic);
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
        return map;
    }
    
       public static Map<String, Integer> getTopicsPaperUsername(String username, String keyword) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT tp.topic, COUNT(tp.topic) as totalTopic FROM Topic tp inner join\n"
                + "                 Paper pp ON tp.topic = pp.topic\n"
                + "                 WHERE username = ? AND (title LIKE ? OR pp.ID LIKE ? OR Description LIKE ?)\n"
                + "               GROUP BY tp.topic";
        Map<String, Integer> map = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, username);
                pre.setString(2, "%"+keyword+"%");
                pre.setString(3, "%"+keyword+"%");
                pre.setString(4, "%"+keyword+"%");
                res = pre.executeQuery();
                while (res.next()) {
                    String topic = res.getString("topic");
                    int totalTopic = res.getInt("totalTopic");

                    if (map == null) {
                        map = new HashMap<>();
                    }
                    map.put(topic, totalTopic);
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
        return map;
    }

      public static Map<String, String> getTopicsPicture() throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM Topic";
        Map<String, String> map = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
        
                res = pre.executeQuery();
                while (res.next()) {
                    String topic = res.getString("topic");
                     String picture = res.getString("picture");

                    if (map == null) {
                        map = new HashMap<>();
                    }
                    map.put(topic, picture);
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
        return map;
    }    
} 
