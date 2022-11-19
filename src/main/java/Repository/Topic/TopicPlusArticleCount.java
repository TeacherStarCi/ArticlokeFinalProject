
package Repository.Topic;

public class TopicPlusArticleCount {
    private TopicDTO topic;
    private int count;

    public TopicPlusArticleCount() {
    }

    public TopicPlusArticleCount(TopicDTO topic, int count) {
        this.topic = topic;
        this.count = count;
    }
    
    
    public TopicDTO getTopic() {
        return topic;
    }

    public void setTopic(TopicDTO topic) {
        this.topic = topic;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TopicPlusArticleCount{" + "topic=" + topic + ", count=" + count + '}';
    }

   
    
}
