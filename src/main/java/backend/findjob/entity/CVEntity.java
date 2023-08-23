package backend.findjob.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "cv")
public class CVEntity extends BaseEntity{
    @Column
    private String url;
    @Column
    private String name;
//    @Column
//    private String type;

//    @Column(name = "cv_data", columnDefinition = "MEDIUMBLOB")
//    @Lob
//    private byte[] cv_data;
    @Column(columnDefinition="TEXT")
    private String info;
    @Column
    @CreatedDate
    private Timestamp create_at;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_job")
    private JobEntity job;

    public JobEntity getJob() {
        return job;
    }

    public void setJob(JobEntity job) {
        this.job = job;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public byte[] getCv_data() {
//        return cv_data;
//    }
//
//    public void setCv_data(byte[] cv_data) {
//        this.cv_data = cv_data;
//    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }
}
