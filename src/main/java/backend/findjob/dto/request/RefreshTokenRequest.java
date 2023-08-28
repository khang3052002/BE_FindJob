package backend.findjob.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RefreshTokenRequest {
    @NotBlank(message = "Username shouldn't blank")
    @NotNull(message = "Username shouldn't be null")
    private String username;
    @NotBlank(message = "Id shouldn't blank")
    @NotNull(message = "Id shouldn't be null")
    private Long id;
    @NotBlank(message = "RfToken shouldn't blank")
    @NotNull(message = "RfToken shouldn't be null")
    private String refreshToken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
