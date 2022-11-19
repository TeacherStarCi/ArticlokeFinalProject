package Utils;

import Repository.Article.ArticleDTO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utils {

    public static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public static String filePath(String location, ServletContext sce) {
        return sce.getAttribute("root").toString() + "\\src\\main\\webapp\\Asset\\" + location;
    }

    public static String conFilePath(String location, ServletContext sce) {
        return sce.getAttribute("root").toString() + "\\src\\main\\webapp\\" + location;
    }

    public static File getFolderUpload(String location, ServletContext sce) {
        String root = sce.getAttribute("root").toString();
        File folderUpload = new File(root + "\\src\\main\\webapp\\Asset\\Image\\" + location);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    public static File getFolderFileUpload(String location, ServletContext sce) {
        String root = sce.getAttribute("root").toString();
        File folderUpload = new File(root + "\\src\\main\\webapp\\Asset\\File\\" + location);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    public static List<ArticleDTO> keywordFilter(String keyword) throws SQLException, ClassNotFoundException {
        String SQL = "SELECT cuong.ID, picture, title, username, topic, description, link, linkDemo, publishedDate, permission,\n"
                + " price, totalReaction,totalDownload, cuong.status FROM\n"
                + "(SELECT chidori.ID, totalReaction, totalDownload FROM \n"
                + "(SELECT ar.ID, COUNT(it.ID) as totalReaction\n"
                + "FROM Paper pp inner join Article ar ON pp.ID = ar.ID\n"
                + "left join Reaction it ON ar.ID = it.ID \n"
                + "WHERE title LIKE ? OR pp.username LIKE ?\n"
                + "OR description LIKE ? GROUP BY ar.ID) chidori INNER JOIN\n"
                + "(SELECT ar.ID, COUNT(downloadedDate) as totalDownload\n"
                + "FROM Paper pp inner join Article ar ON pp.ID = ar.ID\n"
                + "left join Download dl ON ar.ID = dl.ID GROUP BY ar.ID\n"
                + ") kakashi ON chidori.ID = kakashi.ID\n"
                + ") starci inner join\n"
                + "(SELECT ar.ID, picture, title, username, topic, description, link, linkDemo, publishedDate, permission, price, ar.status FROM Article ar inner join Paper pp ON ar.ID = pp.ID) cuong\n"
                + "ON starci.ID = cuong.ID WHERE permission = 'Public'";
        List<ArticleDTO> articles = new ArrayList<>();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DatabaseConnector.makeConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, "%" + keyword + "%");
                pre.setString(2, "%" + keyword + "%");
                pre.setString(3, "%" + keyword + "%");

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

    public static List<ArticleDTO> titleAuthorFiler(String title, String author, List<ArticleDTO> articles) {
        List<ArticleDTO> articlesAfterFilting = new ArrayList<>();

        for (ArticleDTO ar : articles) {
            if (ar.getTitle().toLowerCase().contains(title.toLowerCase()) && ar.getUsername().toLowerCase().contains(author.toLowerCase())) {
                articlesAfterFilting.add(ar);
            }
        }
        return articlesAfterFilting;

    }

    public static List<ArticleDTO> topicFilter(String topic, List<ArticleDTO> articles) {
        List<ArticleDTO> articlesAfterFilting = new ArrayList<>();
        if (articles != null) {
            if (topic.equals("All")) {
                articlesAfterFilting = articles;
            } else {
                for (ArticleDTO ar : articles) {
                    if (ar.getTopic().equals(topic)) {
                        articlesAfterFilting.add(ar);
                    }
                }
            }

        }
        return articlesAfterFilting;
    }

    public static List<ArticleDTO> sortFilter(String sort, List<ArticleDTO> articles) {
        List<ArticleDTO> articlesAfterFilting = articles;
        if (sort.equals("Alphabet")) {
            Collections.sort(articlesAfterFilting, new Comparator<ArticleDTO>() {
                @Override
                public int compare(ArticleDTO a1, ArticleDTO a2) {
                    if (a1.getTitle().compareTo(a2.getTitle()) > 0) {
                        return 1;
                    } else {
                        if (a1.getTitle().equals(a2.getTitle())) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                }
            });
        } else if (sort.equals("Oldest")) {
            Collections.sort(articlesAfterFilting, new Comparator<ArticleDTO>() {
                @Override
                public int compare(ArticleDTO a1, ArticleDTO a2) {
                    if (a2.getPublishedDate().compareTo(a1.getPublishedDate()) < 0) {
                        return 1;
                    } else {
                        if (a1.getPublishedDate().equals(a2.getPublishedDate())) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                }
            });

        } else if (sort.equals("Latest")) {
            Collections.sort(articlesAfterFilting, new Comparator<ArticleDTO>() {
                @Override
                public int compare(ArticleDTO a1, ArticleDTO a2) {
                    if (a2.getPublishedDate().compareTo(a1.getPublishedDate()) > 0) {
                        return 1;
                    } else {
                        if (a1.getPublishedDate().equals(a2.getPublishedDate())) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                }
            });
        }
        return articlesAfterFilting;
    }

    public static List<ArticleDTO> dateFilter(Date date, List<ArticleDTO> articles) {

        List<ArticleDTO> articlesAfterFilting = new ArrayList<>();
        if (date == null) {
            articlesAfterFilting = articles;
        } else {
            for (ArticleDTO ar : articles) {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(ar.getPublishedDate().getTime());
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int year = cal.get(Calendar.YEAR);
                Date convertDate = new Date(year - 1900, month, day);
                if (convertDate.compareTo(date) == 0) {
                    articlesAfterFilting.add(ar);
                }
            }
        }
        return articlesAfterFilting;
    }

    public static DateDTO getDateFromMillis(long milis) {
        long milisecond = milis % 1000;
        long preSecond = milis / 1000;
        long second = preSecond % 60;
        long preMinute = preSecond / 60;
        long minute = preMinute % 60;
        long preHour = preMinute / 60;
        long hour = preHour % 24;
        long date = preHour / 24;

        DateDTO dateObj = new DateDTO(date, hour, minute, second, milisecond);
        return dateObj;
    }

    public static DateDTO getRemainingTime(Timestamp time, boolean advanced) {
        DateDTO dateObj = null;
        long deadline = 1000 * 60 * 60 * 24 * 3;
        if (!advanced) {
            deadline = 1000 * 60 * 60 * 24 * 7;
        }

        Timestamp expired = new Timestamp(time.getTime() + deadline);
        long remaining = expired.getTime() - System.currentTimeMillis();
        if (remaining >= 0) {
            dateObj = getDateFromMillis(remaining);
        }
        return dateObj;
    }

    public static DateDTO getRemainingTimeForPublisher(Timestamp time, boolean advanced) {
        DateDTO dateObj = null;
        long deadline = 1000 * 60 * 60 * 24 * 6;
        if (!advanced) {
            deadline = 1000 * 60 * 60 * 24 * 14;
        }

        Timestamp expired = new Timestamp(time.getTime() + deadline);
        long remaining = expired.getTime() - System.currentTimeMillis();
        if (remaining >= 0) {
            dateObj = getDateFromMillis(remaining);
        }
        return dateObj;
    }

}
