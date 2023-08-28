package backend.findjob.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppreciateDTO {
    @NotBlank(message = "Title shouldn't blank")
    @NotNull(message = "Title shouldn't be null")
    private String title;
    @NotBlank(message = "position shouldn't blank")
    @NotNull(message = "position shouldn't be null")
    private String position;
    @NotBlank(message = "year shouldn't blank")
    @NotNull(message = "year shouldn't be null")
    private Integer year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
