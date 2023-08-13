package backend.findjob.dto.respone.Job;

import backend.findjob.entity.Enum.TypeWork;
import backend.findjob.entity.Enum.TypeWorkPlace;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JobDetailDTO {
    private Long id;
    private String title;
    private Double salary;
    private String time_create;
    private String time_expire;
    private List<String> tags = new ArrayList<>();
    private List<String> facility = new ArrayList<>();
    private String qualification;
    private String experience;
    private TypeWork type_work;
    private String location;

    private String description;
    private String required;
    private TypeWorkPlace type_work_place;

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

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(Timestamp expire_at) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time_expire =  dateFormat.format(expire_at);
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTime_create() {
        return time_create;
    }

    public void setTime_create(Timestamp create_at) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        this.time_create =  dateFormat.format(create_at);
    }

    public List<String> getFacility() {
        return facility;
    }

    public void setFacility(List<String> facility) {
        this.facility = facility;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public TypeWork getType_work() {
        return type_work;
    }

    public void setType_work(TypeWork type_work) {
        this.type_work = type_work;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public TypeWorkPlace getType_work_place() {
        return type_work_place;
    }

    public void setType_work_place(TypeWorkPlace type_work_place) {
        this.type_work_place = type_work_place;
    }
}
