package Repository.Article;

import Utils.DatabaseConnector;
import Utils.Utils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO implements Serializable {

    public static List<ArticleDTO> getArticlesKeywordTopic(String username_, String keyword, String currentTopic) throws SQLException, ClassNotFoundException {
        String SQL = " SELECT tu.ID, picture, title, username, topic, description, link, linkDemo,\n"
                + "  publishedDate, permission, price, totalReaction, totalDownload, tu.status \n"
                + "  FROM\n"
                + "  (SELECT chidori.ID, totalReaction, totalDownload FROM \n"
                + "(SELECT ar.ID, COUNT(it.ID) as totalReaction\n"
                + "FROM Paper pp inner join Article ar ON pp.ID = ar.ID\n"
                + "left join Reaction it ON ar.ID = it.ID \n"
                + "WHERE pp.username = ?\n AND (pp.title like ? OR pp.description like ? OR ar.ID like ?) AND pp.topic = ? \n"
                + "GROUP BY ar.ID\n"
                + ") chidori INNER JOIN\n"
                + "(SELECT ar.ID, COUNT(downloadedDate) as totalDownload\n"
                + "FROM Paper pp inner join Article ar ON pp.ID = ar.ID\n"
                + "left join Download dl ON ar.ID = dl.ID GROUP BY ar.ID\n"
                + ") kakashi ON chidori.ID = kakashi.ID) cuong\n"
                + "			 inner join (SELECT ar.ID, picture, title, username, topic, description, link, linkDemo, \n"
                + "			 publishedDate, permission, price, ar.status FROM Article ar inner join Paper pp ON ar.ID = pp.ID)\n"
                + "			 tu ON cuong.ID = tu.ID";
        List<ArticleDTO> articles = new ArrayList<>();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                if (currentTopic.equals("All")) {
                    SQL = "SELECT tu.ID, picture, title, username, topic, description, link, linkDemo,\n"
                            + "  publishedDate, permission, price, totalReaction, totalDownload, tu.status \n"
                            + "  FROM\n"
                            + "  (SELECT chidori.ID, totalReaction, totalDownload FROM \n"
                            + "(SELECT ar.ID, COUNT(it.ID) as totalReaction\n"
                            + "FROM Paper pp inner join Article ar ON pp.ID = ar.ID\n"
                            + "left join Reaction it ON ar.ID = it.ID \n"
                            + "WHERE pp.username = ?\n AND (pp.title like ? OR pp.description like ? OR ar.ID like ?) \n"
                            + "GROUP BY ar.ID\n"
                            + ") chidori INNER JOIN\n"
                            + "(SELECT ar.ID, COUNT(downloadedDate) as totalDownload\n"
                            + "FROM Paper pp inner join Article ar ON pp.ID = ar.ID\n"
                            + "left join Download dl ON ar.ID = dl.ID GROUP BY ar.ID\n"
                            + ") kakashi ON chidori.ID = kakashi.ID) cuong\n"
                            + "			 inner join (SELECT ar.ID, picture, title, username, topic, description, link, linkDemo, \n"
                            + "			 publishedDate, permission, price, ar.status FROM Article ar inner join Paper pp ON ar.ID = pp.ID)\n"
                            + "			 tu ON cuong.ID = tu.ID";
                    pre = con.prepareStatement(SQL);
                    pre.setString(1, username_);
                    pre.setString(2, "%" + keyword + "%");
                    pre.setString(3, "%" + keyword + "%");
                    pre.setString(4, "%" + keyword + "%");
                } else {
                    pre = con.prepareStatement(SQL);
                    pre.setString(1, username_);
                    pre.setString(2, "%" + keyword + "%");
                    pre.setString(3, "%" + keyword + "%");
                    pre.setString(4, "%" + keyword + "%");
                    pre.setString(5, currentTopic);
                }
                res = pre.executeQuery();

                while (res.next()) {
                    String ID = res.getString("ID");
                    String picture = res.getString("picture");
                    String title = res.getString("title");
                    String username = res.getString("username");
                    String topic = res.getString("topic");
                    String description = res.getString("description");

                    String link = res.getString("link");
                    String linkDemo = res.getString("linkDemo");
                    Timestamp publishedDate = res.getTimestamp("publishedDate");
                    String permission = res.getString("permission");

                    float price = res.getFloat("price");
                    int totalReaction = res.getInt("totalReaction");
                    int totalDownload = res.getInt("totalDownload");
                    boolean status = res.getBoolean("status");

                    ArticleDTO article = new ArticleDTO(ID, picture, title, username, topic, description, link, linkDemo, publishedDate, permission, price, totalReaction, totalDownload, status);
                    articles.add(article);
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
        return articles;
    }

    public static List<ArticleDTO> getArticlesSearch(String keyword, String title, String author, String topic, String sort, Date date)
            throws SQLException, ClassNotFoundException {

        List<ArticleDTO> articlesAfterKeyword = Utils.keywordFilter(keyword);
        List<ArticleDTO> articlesAfterTitleAuthor = Utils.titleAuthorFiler(title, author, articlesAfterKeyword);
        List<ArticleDTO> articlesAfterTopic = Utils.topicFilter(topic, articlesAfterTitleAuthor);
        List<ArticleDTO> articlesAfterSort = Utils.sortFilter(sort, articlesAfterTopic);
        return Utils.dateFilter(date, articlesAfterSort);

    }

    public static List<ArticleDTO> getArticlesUsernameLatestDate(String username_) throws SQLException, ClassNotFoundException {
        String SQL = " SELECT tu.ID, picture, title, username, topic, description, link, linkDemo,\n"
                + "  publishedDate, permission, price, totalReaction, totalDownload, tu.status \n"
                + "  FROM\n"
                + "  (SELECT chidori.ID, totalReaction, totalDownload FROM \n"
                + "(SELECT ar.ID, COUNT(it.ID) as totalReaction\n"
                + "FROM Paper pp inner join Article ar ON pp.ID = ar.ID\n"
                + "left join Reaction it ON ar.ID = it.ID \n"
                + "WHERE pp.username = ?\n"
                + "GROUP BY ar.ID\n"
                + ") chidori INNER JOIN\n"
                + "(SELECT ar.ID, COUNT(downloadedDate) as totalDownload\n"
                + "FROM Paper pp inner join Article ar ON pp.ID = ar.ID\n"
                + "left join Download dl ON ar.ID = dl.ID GROUP BY ar.ID\n"
                + ") kakashi ON chidori.ID = kakashi.ID) cuong\n"
                + "			 inner join (SELECT ar.ID, picture, title, username, topic, description, link, linkDemo, \n"
                + "			 publishedDate, permission,  price, ar.status FROM Article ar inner join Paper pp ON ar.ID = pp.ID)\n"
                + "			 tu ON cuong.ID = tu.ID\n"
                + "			 ORDER BY publishedDate DESC";
        List<ArticleDTO> articles = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {

                pre = con.prepareStatement(SQL);
                pre.setString(1, username_);
                res = pre.executeQuery();

                while (res.next()) {
                    String ID = res.getString("ID");
                    String picture = res.getString("picture");
                    String title = res.getString("title");
                    String username = res.getString("username");
                    String topic = res.getString("topic");
                    String description = res.getString("description");

                    String link = res.getString("link");
                    String linkDemo = res.getString("linkDemo");
                    Timestamp publishedDate = res.getTimestamp("publishedDate");
                    String permission = res.getString("permission");
                    float price = res.getFloat("price");
                    int totalReaction = res.getInt("totalReaction");
                    int totalDownload = res.getInt("totalDownload");
                    boolean status = res.getBoolean("status");

                    ArticleDTO article = new ArticleDTO(ID, picture, title, username, topic, description, link, linkDemo, publishedDate, permission, price, totalReaction, totalDownload, status);
                    if (articles == null) {
                        articles = new ArrayList<>();
                    }
                    articles.add(article);
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
        return articles;
    }

    public static List<ArticleDTO> getArticlesLastedPublishDate() throws SQLException, ClassNotFoundException {
        String SQL = "SELECT ID, sta.picture, title, sta.username, topic, description, link, linkDemo,\n"
                + "                   publishedDate, permission, price, totalReaction, totalDownload, sta.status  \n"
                + "FROM (SELECT tu.ID, picture, title, username, topic, description, link, linkDemo,\n"
                + "                   publishedDate, permission, price, totalReaction, totalDownload, tu.status \n"
                + "                   FROM\n"
                + "                   (SELECT chidori.ID, totalReaction, totalDownload FROM \n"
                + "                 (SELECT ar.ID, COUNT(it.ID) as totalReaction\n"
                + "                 FROM Paper pp inner join Article ar ON pp.ID = ar.ID\n"
                + "                 left join Reaction it ON ar.ID = it.ID \n"
                + "                 GROUP BY ar.ID\n"
                + "                 ) chidori INNER JOIN\n"
                + "                 (SELECT ar.ID, COUNT(downloadedDate) as totalDownload\n"
                + "                 FROM Paper pp inner join Article ar ON pp.ID = ar.ID\n"
                + "                 left join Download dl ON ar.ID = dl.ID GROUP BY ar.ID\n"
                + "                 ) kakashi ON chidori.ID = kakashi.ID) cuong\n"
                + "                 			 inner join (SELECT ar.ID, picture, title, username, topic, description, link, linkDemo, \n"
                + "                 			 publishedDate, permission, price, ar.status FROM Article ar inner join Paper pp ON ar.ID = pp.ID)\n"
                + "                 			 tu ON cuong.ID = tu.ID\n"
                + "                 			) sta inner join\n"
                + "User_ us ON sta.username = us.username\n"
                + " ORDER BY publishedDate DESC";
        List<ArticleDTO> articles = new ArrayList<>();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                res = pre.executeQuery();
                while (res.next()) {
                    String ID = res.getString("ID");
                    String picture = res.getString("picture");
                    String title = res.getString("title");
                    String username = res.getString("username");
                    String topic = res.getString("topic");
                    String description = res.getString("description");

                    String link = res.getString("link");
                    String linkDemo = res.getString("linkDemo");
                    Timestamp publishedDate = res.getTimestamp("publishedDate");
                    String permission = res.getString("permission");
                 
                    float price = res.getFloat("price");
                    int totalReaction = res.getInt("totalReaction");
                    int totalDownload = res.getInt("totalDownload");
                    boolean status = res.getBoolean("status");

                    ArticleDTO article = new ArticleDTO(ID, picture, title, username, topic, description, link, linkDemo, publishedDate, permission, price, totalReaction, totalDownload, status);
                    articles.add(article);
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
        return articles;
    }
    
    

    public static ArticleDTO getArticle(String ID) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT tu.ID, picture, title, username, topic, description, link, linkDemo,\n"
                + "             publishedDate, permission, price, totalReaction, totalDownload, tu.status \n"
                + "               FROM\n"
                + "               (SELECT chidori.ID, totalReaction, totalDownload FROM\n"
                + "             (SELECT ar.ID, COUNT(it.ID) as totalReaction\n"
                + "              FROM Paper pp inner join Article ar ON pp.ID = ar.ID\n"
                + "              left join Reaction it ON ar.ID = it.ID \n"
                + "                WHERE ar.ID = ?\n"
                + "			 GROUP BY ar.ID\n"
                + "			\n"
                + "              ) chidori INNER JOIN\n"
                + "               (SELECT ar.ID, COUNT(downloadedDate) as totalDownload\n"
                + "             FROM Paper pp inner join Article ar ON pp.ID = ar.ID\n"
                + "           left join Download dl ON ar.ID = dl.ID GROUP BY ar.ID\n"
                + "              ) kakashi ON chidori.ID = kakashi.ID) cuong\n"
                + "               		 inner join (SELECT ar.ID, picture, title, username, topic, description, link, linkDemo,\n"
                + "               			 publishedDate, permission,  price, ar.status FROM Article ar inner join Paper pp ON ar.ID = pp.ID)\n"
                + "              		 tu ON cuong.ID = tu.ID";
        ArticleDTO article = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, ID);
                res = pre.executeQuery();
                while (res.next()) {
                    String picture = res.getString("picture");
                    String title = res.getString("title");
                    String username = res.getString("username");
                    String topic = res.getString("topic");
                    String description = res.getString("description");

                    String link = res.getString("link");
                    String linkDemo = res.getString("linkDemo");
                    Timestamp publishedDate = res.getTimestamp("publishedDate");
                    String permission = res.getString("permission");
                  
                    float price = res.getFloat("price");
                    int totalReaction = res.getInt("totalReaction");
                    int totalDownload = res.getInt("totalDownload");
                    boolean status = res.getBoolean("status");

                    article = new ArticleDTO(ID, picture, title, username, topic, description, link, linkDemo, publishedDate, permission, price, totalReaction, totalDownload, status);
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
        return article;
    }

    public static boolean updateArticle(ArticleDTO article)
            throws SQLException, ClassNotFoundException {

        String SQL = "UPDATE Article SET link = ? , linkDemo = ? , publishedDate = ?, permission = ?, price = ?, status = ?"
                + " WHERE ID = ?";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);

            
                pre.setString(1, article.getLink());
                pre.setString(2, article.getLinkDemo());
                pre.setTimestamp(3, article.getPublishedDate());
                pre.setString(4, article.getPermission());
                pre.setFloat(5, article.getPrice());
                pre.setBoolean(6, article.isStatus());
                pre.setString(7, article.getID());
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
    public static boolean addArticle(ArticleDTO article)
            throws SQLException, ClassNotFoundException {

        String SQL = "INSERT INTO Article(ID, link, linkDemo, publishedDate, permission"
                + ",price, status)"
                + " VALUES(?,?,?,?,?,?,?)";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, article.getID());
                pre.setString(2, article.getLink());
                pre.setString(3, article.getLinkDemo());
                pre.setTimestamp(4, article.getPublishedDate());
                pre.setString(5, article.getPermission());
                pre.setFloat(6, article.getPrice());
                pre.setBoolean(7, article.isStatus());
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
