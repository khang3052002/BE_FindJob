package backend.findjob.dto.request;

public class CreateCompanyRequest {
    private String name;
    private String image;
    private String website;
    private String introduce;
    private String location;
    private String type_company;
    private Integer employee_number;
    private String specialization;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType_company() {
        return type_company;
    }

    public void setType_company(String type_company) {
        this.type_company = type_company;
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
}
