/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository.Paper;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author tucuo
 */
public class PaperDTO implements Serializable{
    private String ID;
    private String title;
    private String picture;
    private String topic;
    private String description;
    private String content;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String username;
    private String publishedStatus;
    private boolean advanced;
    private Timestamp submittedDate;
    private boolean status;

    public boolean isAdvanced() {
        return advanced;
    }

    public void setAdvanced(boolean advanced) {
        this.advanced = advanced;
    }

    public Timestamp getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Timestamp submittedDate) {
        this.submittedDate = submittedDate;
    }

    @Override
    public String toString() {
        return "PaperDTO{" + "ID=" + ID + ", title=" + title + ", picture=" + picture + ", topic=" + topic + ", description=" + description + ", content=" + content + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", username=" + username + ", publishedStatus=" + publishedStatus + ", advanced=" + advanced + ", submittedDate=" + submittedDate + ", status=" + status + '}';
    }
 
   
    
    public String getPublishedStatus() {
        return publishedStatus;
    }

    public void setPublishedStatus(String publishedStatus) {
        this.publishedStatus = publishedStatus;
    }
  
    public PaperDTO() {
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public PaperDTO(String ID, String title, String picture, String topic, String description, String content, Timestamp createdDate, Timestamp modifiedDate, String username, String publishedStatus, boolean advanced, Timestamp submittedDate, boolean status) {
        this.ID = ID;
        this.title = title;
        this.picture = picture;
        this.topic = topic;
        this.description = description;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.username = username;
        this.publishedStatus = publishedStatus;
        this.advanced = advanced;
        this.submittedDate = submittedDate;
        this.status = status;
    }

  

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
