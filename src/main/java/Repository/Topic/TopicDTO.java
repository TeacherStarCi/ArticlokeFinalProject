
package Repository.Topic;

import java.io.Serializable;

public class TopicDTO implements Serializable  {
    private String topic;
    private String picture;

    public TopicDTO(String topic, String picture) {
        this.topic = topic;
        this.picture = picture;
    }

    public TopicDTO() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "TopicDTO{" + "topic=" + topic + ", picture=" + picture + '}';
    }
    
}
