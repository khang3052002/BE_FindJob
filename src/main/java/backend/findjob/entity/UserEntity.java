package backend.findjob.entity;

import backend.findjob.entity.Enum.Gender;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity implements UserDetails {
    @Column
    private String name;

//    @Column
//    private String image;
    @Column
    private Date dob;

    @Column(columnDefinition = "ENUM('Male', 'Female')")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String email;
    @Column
    private String phone;
    @Column
    private String location;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String id_device;
    @Column
    private String skill;
    @Column
    private String about_me;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_image", referencedColumnName = "id")
    private ImageUserEntity imageUser;
    @ManyToMany
    @JoinTable(name="user_job_save",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_job"))
    private List<JobEntity> listJobSave = new ArrayList<>();

    @ManyToMany(mappedBy = "listUser")
    private List<NotifyEntity> listNotify = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<CVEntity> listCV = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<WorkExpEntity> listWorkExp = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<EducationEntity> listEducation = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<AppreciateEntity> listAppreciate = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<LanguageExpEntity> listLangExp = new ArrayList<>();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public ImageUserEntity getImageUser() {
        return imageUser;
    }

    public void setImageUser(ImageUserEntity imageUser) {
        this.imageUser = imageUser;
    }

    public void setListJobSave(List<JobEntity> listJobSave) {
        this.listJobSave = listJobSave;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_device() {
        return id_device;
    }

    public void setId_device(String id_device) {
        this.id_device = id_device;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<JobEntity> getListJobSave() {
        return listJobSave;
    }

    public void setListJobSave(ArrayList<JobEntity> listJobSave) {
        this.listJobSave = listJobSave;
    }

    public List<CVEntity> getListCV() {
        return listCV;
    }

    public void setListCV(List<CVEntity> listCV) {
        this.listCV = listCV;
    }

    public List<NotifyEntity> getListNotify() {
        return listNotify;
    }

    public void setListNotify(List<NotifyEntity> listNotify) {
        this.listNotify = listNotify;
    }

    public List<WorkExpEntity> getListWorkExp() {
        return listWorkExp;
    }

    public void setListWorkExp(List<WorkExpEntity> listWorkExp) {
        this.listWorkExp = listWorkExp;
    }

    public List<EducationEntity> getListEducation() {
        return listEducation;
    }

    public void setListEducation(List<EducationEntity> listEducation) {
        this.listEducation = listEducation;
    }

    public List<AppreciateEntity> getListAppreciate() {
        return listAppreciate;
    }

    public void setListAppreciate(List<AppreciateEntity> listAppreciate) {
        this.listAppreciate = listAppreciate;
    }

    public List<LanguageExpEntity> getListLangExp() {
        return listLangExp;
    }

    public void setListLangExp(List<LanguageExpEntity> listLangExp) {
        this.listLangExp = listLangExp;
    }
}
