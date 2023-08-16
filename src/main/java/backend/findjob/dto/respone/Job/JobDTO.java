package backend.findjob.dto.respone.Job;

import backend.findjob.dto.respone.PageDTO;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class JobDTO {
    private Long id;
    private String title;
    private Double salary;
    private String time_create;

    private String nameCompany;
    private Long id_company;
    private Boolean is_saved;
    private List<String> tags = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getTime_create() {
        return time_create;
    }

    public void setTime_create(Timestamp create_at) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        this.time_create =  dateFormat.format(create_at);

    }


    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public Long getId_company() {
        return id_company;
    }

    public void setId_company(Long id_company) {
        this.id_company = id_company;
    }

    public Boolean getIs_saved() {
        return is_saved;
    }

    public void setIs_saved(Boolean is_saved) {
        this.is_saved = is_saved;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
