package backend.findjob.dto.request;

public class PushNotificationRequest {
    private String device_token;
    private String title;
    private String description;
    private String topic;

    public PushNotificationRequest(String device_token, String title, String description, String topic) {
        this.device_token = device_token;
        this.title = title;
        this.description = description;
        this.topic = topic;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
