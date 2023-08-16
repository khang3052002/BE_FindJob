package backend.findjob.api.AuthAPI;

import backend.findjob.dto.request.RefreshTokenRequestDTO;
import backend.findjob.dto.request.SignInRequestDTO;
import backend.findjob.dto.request.SignUpRequestDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth")
public class AuthAPI {

    @Autowired
    private IAuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<ResponeObject> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO)
    {
        return authService.register(signUpRequestDTO);
    }
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json")
                        ,required = true,
                    description = "Only one field username or email"
                    )
    )
    @PostMapping("/signin")
    public ResponseEntity<ResponeObject> signIn(@RequestBody SignInRequestDTO signInRequestDTO)
    {
        return authService.login(signInRequestDTO);
    }
    @PostMapping("/refreshToken")
    public  ResponseEntity<ResponeObject> refreshToken (@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO)
    {
        return authService.refreshToken(refreshTokenRequestDTO);
    }
}
