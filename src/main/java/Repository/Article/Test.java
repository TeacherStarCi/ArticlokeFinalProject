package Repository.Article;

import Utils.Utils;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Test {

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {
        ArticleDTO ar = new ArticleDTO("AR9", "1", "1", "starci", "1", "1", "1", "1", new Timestamp(0), "1", 0, 0, 0, true);
  
  ArticleDAO.updateArticle(ar);
        
        
        
    }}
    
    

