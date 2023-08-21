package backend.findjob.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "education")
public class EducationEntity extends BaseEntity{
    @Column
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String school;
    @Column
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date start_date;
    @Column
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date end_date;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
