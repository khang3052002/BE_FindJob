package backend.findjob.entity;

import backend.findjob.entity.Enum.Gender;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class CompanyEntity extends BaseEntity {
    @Column
    private String name;
    @Column
    private String image;
    @Column
    private String website;

    @Column(columnDefinition = "TEXT")
    private String introduce;
    @Column
    private Integer employee_number;
    @Column
    private String location;
    @Column
    private String specialization;
    @Column
    private String type_company;

    @OneToMany(mappedBy = "company")
    private List<JobEntity> listJob = new ArrayList<>();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(Integer employee_number) {
        this.employee_number = employee_number;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getType_company() {
        return type_company;
    }

    public void setType_company(String type_company) {
        this.type_company = type_company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
