package Controllers.Workspace;

public class PaperErrorCatcher {

    public static String checkTitleLength(String title) {
        String error = null;
        if (title.isEmpty()) {
            error = "Please type title.";
        } else if (title.length() < 25) {
            error = "Title is too short (at least 25 characters).";
        } else if (title.length() > 150) {
            error = "Title is too long (at most 150 characters)";
        }
        return error;
    }
     public static String checkDescriptionLength(String description) {
        String error = null;
        if (description.isEmpty()) {
            error = "Please type description.";
        } else if (description.length() < 50) {
            error = "Description is too short (at least 50 characters).";
        } else if (description.length() > 500) {
            error = "Description is too long (at most 500 characters)";
        }
        return error;
    }
       public static String checkAnswerLength(String answer) {
        String error = null;
        if (answer.isEmpty()) {
            error = "Please type description.";
        } else if (answer.length() < 50) {
            error = "Answer is too short (at least 50 characters).";
        } else if (answer.length() > 500) {
            error = "Answer is too long (at most 500 characters)";
        }
        return error;
    }
     
      public static String checkContentLength(String content) {
        String error = null;
        if (content.isEmpty()) {
            error = "Please type content.";
        } else if (content.length() < 100) {
            error = "Content is too short (at least 100 characters).";
        } else if (content.length() > 2000) {
            error = "Content is too long (at most 2000 characters)";
        } 
        return error;
    }
       public static String checkFileName(String fileName) {
        String error = null;
        if (fileName == null || fileName.isEmpty()) {
            error = "Please upload picture.";
        }
        return error;
    }
}
