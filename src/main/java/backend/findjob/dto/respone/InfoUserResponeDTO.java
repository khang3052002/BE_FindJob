package backend.findjob.dto.respone;

public class InfoUserResponeDTO {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String image;
    private String accessToken;
    private String refreshToken;

    public InfoUserResponeDTO(Long id, String username, String email, String name, String image, String accessToken, String refreshToken) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.image = image;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
