package backend.findjob.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SignUpRequest {
    @NotBlank(message = "Username shouldn't blank")
    @NotNull(message = "Username shouldn't be null")
    private String username;
    @NotBlank(message = "Password shouldn't blank")
    @NotNull(message = "Password shouldn't be null")
    private String password;
    @NotBlank(message = "Name shouldn't blank")
    @NotNull(message = "Name shouldn't be null")
    private String name;
    @Email(message = "Invalid email")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
