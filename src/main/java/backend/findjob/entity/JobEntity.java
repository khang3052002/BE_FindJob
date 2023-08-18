package backend.findjob.entity;

import backend.findjob.entity.Enum.Gender;
import backend.findjob.entity.Enum.TypeWork;
import backend.findjob.entity.Enum.TypeWorkPlace;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job")
public class JobEntity extends  BaseEntity{
    @Column
    private String title;
    @Column(columnDefinition="TEXT")
    private String description;

    @Column
    @CreatedDate
    private Timestamp create_at;
    @Column
    private Timestamp expire_at;
    @Column(columnDefinition="TEXT")
    private String required;

    @Column
    private Double salary;
    @Column
    private String position;
    @Column
    private String qualification;

    @Column
    private String experience;
    @Column
    private String location;
    @Column(columnDefinition="TEXT")
    private String facilities;
    @Column
    private String specialization;

    @Column(columnDefinition = "ENUM('Fulltime', 'Parttime', 'Contract', 'Temporary', 'Volunteer', 'Apprenticeship')")
    @Enumerated(EnumType.STRING)
    private TypeWork type_work;

    @Column(columnDefinition = "ENUM('On-site', 'Hybrid', 'Remote')")
    @Enumerated(EnumType.STRING)
    private TypeWorkPlace type_work_place;

    @ManyToMany(mappedBy = "listJobSave")
    private List<UserEntity> listUser = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_company", nullable=false)
    private CompanyEntity company;

    @OneToMany(mappedBy = "job")
    private List<CVEntity> listCV = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_province")
    private ProvinceEntity province;
    public List<CVEntity> getListCV() {
        return listCV;
    }

    public void setListCV(List<CVEntity> listCV) {
        this.listCV = listCV;
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

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getExpire_at() {
        return expire_at;
    }

    public void setExpire_at(Timestamp expire_at) {
        this.expire_at = expire_at;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public TypeWork getType_work() {
        return type_work;
    }

    public void setType_work(TypeWork type_work) {
        this.type_work = type_work;
    }

    public TypeWorkPlace getType_work_place() {
        return type_work_place;
    }

    public void setType_work_place(TypeWorkPlace type_work_place) {
        this.type_work_place = type_work_place;
    }

    public List<UserEntity> getListUser() {
        return listUser;
    }

    public void setListUser(List<UserEntity> listUser) {
        this.listUser = listUser;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
    }
}
