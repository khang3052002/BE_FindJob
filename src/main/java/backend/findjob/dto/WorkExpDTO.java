package backend.findjob.dto;

import java.sql.Date;
//@Builder
public class WorkExpDTO {
    private String title;
    private String description;
    private Boolean is_position_now;
    private String company;
    private Date start_date;
    private Date end_date;



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

    public Boolean getIs_position_now() {
        return is_position_now;
    }

    public void setIs_position_now(Boolean is_position_now) {
        this.is_position_now = is_position_now;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
}
