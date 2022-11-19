package Repository.Paper;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class Test {

    public static void main(String[] args)
            throws SQLException, ClassNotFoundException {
        Timestamp date1 = new Timestamp(2012 - 3 - 2);
        Timestamp date2 = null;
        PaperDTO paper = new PaperDTO("AR1", "StarCi", "12", "Mathematics", "ublic class Test {\n"
                + "     public static void main(String[] args) \n"
                + "            throws SQLException, ClassNotFoundException{\n"
                + "         Date date1 = new Date(2012-3-2);\n"
                + "         Date date2 = null;\n"
                + "   PaperDTO paper = new PaperDTO(PaperDAO.getNextID(), \"Zugi\", \"12\", \"Mathematics\", \"non\", \"non\", date1, null, \"starci\", \"ds\", true);\n"
                + "//        System.out.println(PaperDAO.addPaper(paper));\n"
                + "System.out.println(PaperDAO.addPaper(pap", "ublic class Test {\n"
                + "     public static void main(String[] args) \n"
                + "            throws SQLException, ClassNotFoundException{\n"
                + "         Date date1 = new Date(2012-3-2);\n"
                + "         Date date2 = null;\n"
                + "   PaperDTO paper = new PaperDTO(PaperDAO.getNextID(), \"Zugi\", \"12\", \"Mathematics\", \"non\", \"non\", date1, null, \"starci\", \"ds\", true);\n"
                + "//        System.out.println(PaperDAO.addPaper(paper));\n"
                + "System.out.println(PaperDAO.addPaper(pap", date1, null, "starci", "ds", true,new Timestamp(0), true);
    //    System.out.println(PaperDAO.updatePaper(paper));
    //    System.out.println(PaperDAO.getPapersUsernameLastedModifiedDate("starci"));
      //  System.out.println(PaperDAO.getPapersKeywordTopic("starci", "", "All").size());
        List<PaperDTO> list = PaperDAO.papersKeywordFilter("a", "Pending");
        System.out.println(PaperDAO.papersTopicFilter("Animal", list));
        //System.out.println(PaperDAO.papersDisplayFilter("Latest", list));
      for (PaperDTO paperTr : list){
              System.out.println(paperTr.getID());
          System.out.println(paperTr.getSubmittedDate());
      }
        System.out.println(PaperDAO.searchPaperWorkspace("c","Pending" ,"All", "All", true));
    
    }
    
}
