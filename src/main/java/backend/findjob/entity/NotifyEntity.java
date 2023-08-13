package backend.findjob.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.util.ArrayList;

@Entity
@Table(name = "notify")
public class NotifyEntity extends BaseEntity{
    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    @CreatedDate
    private Date create_at;

    @Column
    private String url;

    @ManyToMany
    @JoinTable(name="user_notify",
            joinColumns = @JoinColumn(name = "id_notify"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private ArrayList<UserEntity> listUser = new ArrayList<>();

    public ArrayList<UserEntity> getListUser() {
        return listUser;
    }

    public void setListUser(ArrayList<UserEntity> listUser) {
        this.listUser = listUser;
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

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
