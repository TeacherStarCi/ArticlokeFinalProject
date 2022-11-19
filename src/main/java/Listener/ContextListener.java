package Listener;

import Repository.Article.ArticleDAO;
import Repository.Article.ArticleDTO;
import Repository.Notification.NotificationDAO;
import Repository.Notification.NotificationDTO;
import Repository.Paper.PaperDAO;
import Repository.Paper.PaperDTO;
import Repository.Topic.TopicDAO;
import Repository.Topic.TopicDTO;
import Repository.Topic.TopicPlusArticleCount;
import Repository.User.UserDAO;
import Repository.User.UserDTO;
import Utils.DateDTO;
import Utils.Utils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        List<ArticleDTO> articlesLatestPublishedDate = new ArrayList<>();
        List<TopicPlusArticleCount> topicsMostCount = new ArrayList<>();
        List<TopicDTO> topics = null;
        List<PaperDTO> pendingPapers = null;
        List<PaperDTO> approvedPapers = null;

        Map<String, String> topicsPicture = null;
        String realPath = context.getRealPath("");
        int index = realPath.lastIndexOf("target");
        String root = null;
        if (index > 0) {
            root = realPath.substring(0, index - 1);
        }
        context.setAttribute("root", root);
        int quant = 0;
        try {
            quant = ArticleDAO.getArticlesLastedPublishDate().size();
        } catch (SQLException | ClassNotFoundException ex) {
        }
        if (quant > 0) {
            try {
                for (int i = 0; i < Math.min(7, ArticleDAO.getArticlesLastedPublishDate().size()); i++) {
                    articlesLatestPublishedDate.add(ArticleDAO.getArticlesLastedPublishDate().get(i));
                }
            } catch (SQLException | ClassNotFoundException ex) {
            }
        }
        try {
            topics = TopicDAO.getTopics();
        } catch (SQLException | ClassNotFoundException ex) {
        }
        try {
            topicsPicture = TopicDAO.getTopicsPicture();
        } catch (SQLException | ClassNotFoundException ex) {
        }
        try {
            for (int i = 0; i < 6; i++) {
                topicsMostCount = TopicDAO.getTopicsMostCount();
            }
        } catch (SQLException | ClassNotFoundException ex) {
        }

        try {
            pendingPapers = PaperDAO.getPapersPublishedStatus("Pending");
        } catch (SQLException | ClassNotFoundException ex) {
        }
        try {
            approvedPapers = PaperDAO.getPapersPublishedStatus("Approved");
        } catch (SQLException | ClassNotFoundException ex) {
        }

        for (PaperDTO paper : pendingPapers) {
            DateDTO date = Utils.getRemainingTime(paper.getSubmittedDate(), paper.isAdvanced());
            if (date == null) {
                paper.setPublishedStatus("Draft");
                paper.setSubmittedDate(null);
                UserDTO user = null;
                try {
                    user = UserDAO.getUserUsername(paper.getUsername());
                } catch (SQLException | ClassNotFoundException ex) {
                }
                user.setBalance(user.getBalance() + 196);
                String header = "Paper Reviewing Expired Submission - " + paper.getID();
                String content = "We apologise for not reviewing your pending paper on time. We have retrieved the paper back as Draft, and have compensated you 196$, our dear.";
                NotificationDTO noti = null;
                try {
                    noti = new NotificationDTO(NotificationDAO.getNextID(), paper.getUsername(), header, content, new Timestamp(System.currentTimeMillis()), true);
                } catch (SQLException | ClassNotFoundException ex) {
                }
                try {
                    NotificationDAO.addNoti(noti);
                } catch (SQLException | ClassNotFoundException ex) {
                }
                try {
                    UserDAO.updateUser(user.getUsername(), user);
                    PaperDAO.updatePaper(paper);
                } catch (SQLException | ClassNotFoundException ex) {
                }

            }
        }

        for (PaperDTO paper : approvedPapers) {
            DateDTO date = Utils.getRemainingTimeForPublisher(paper.getSubmittedDate(), paper.isAdvanced());
            if (date == null) {
                paper.setPublishedStatus("Draft");
                paper.setSubmittedDate(null);
                UserDTO user = null;
                try {
                    user = UserDAO.getUserUsername(paper.getUsername());
                } catch (SQLException | ClassNotFoundException ex) {
                }
                user.setBalance(user.getBalance() + 196);
                String header = "Paper Publishing Expired Submission - " + paper.getID();
                String content = "We apologise for not publishing your pending paper on time. We have retrieved the paper back as Draft, and have compensated you 196$, our dear.";
                NotificationDTO noti = null;
                try {
                    noti = new NotificationDTO(NotificationDAO.getNextID(), paper.getUsername(), header, content, new Timestamp(System.currentTimeMillis()), true);
                } catch (SQLException | ClassNotFoundException ex) {
                }
                try {
                    NotificationDAO.addNoti(noti);
                } catch (SQLException | ClassNotFoundException ex) {
                }
                try {
                    UserDAO.updateUser(user.getUsername(), user);
                    PaperDAO.updatePaper(paper);
                } catch (SQLException | ClassNotFoundException ex) {
                }

            }

        }
        context.setAttribute("articlesLatestPublishedDate", articlesLatestPublishedDate);
        context.setAttribute("topicsMostCount", topicsMostCount);
        context.setAttribute("topics", topics);
        context.setAttribute("topicsPicture", topicsPicture);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
