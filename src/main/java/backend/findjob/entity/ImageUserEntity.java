package backend.findjob.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "image_user")
public class ImageUserEntity extends BaseEntity{
    @Column
    private String url;
    @Column
    private String type;
    @Column(name = "image_data", columnDefinition = "MEDIUMBLOB")
    @Lob
    private byte[] image_data;
    @Column
    @CreatedDate
    private Timestamp create_at;


    @OneToOne(mappedBy = "imageUser")
    private UserEntity user;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getImage_data() {
        return image_data;
    }

    public void setImage_data(byte[] image_data) {
        this.image_data = image_data;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }
}
