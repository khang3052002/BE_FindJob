package backend.findjob.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

//@Builder
public class EducationDTO {
    @NotBlank(message = "Title shouldn't blank")
    @NotNull(message = "Title shouldn't be null")
    private String title;

    private String description;
    @NotBlank(message = "school shouldn't blank")
    @NotNull(message = "school shouldn't be null")
    private String school;
    @NotBlank(message = "start_date shouldn't blank")
    @NotNull(message = "start_date shouldn't be null")
    private Date start_date;
    @NotBlank(message = "end_date shouldn't blank")
    @NotNull(message = "end_date shouldn't be null")
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
}
