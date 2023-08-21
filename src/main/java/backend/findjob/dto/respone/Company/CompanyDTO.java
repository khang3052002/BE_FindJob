package backend.findjob.dto.respone.Company;

public class CompanyDTO {
    private Long id;
    private String name;
    private String image;
    private String website;
    private String introduce;
    private String location;
    private String type_company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
