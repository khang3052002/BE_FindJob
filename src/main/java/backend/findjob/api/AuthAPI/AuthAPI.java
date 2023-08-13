package backend.findjob.api.AuthAPI;

import backend.findjob.dto.request.RefreshTokenRequestDTO;
import backend.findjob.dto.request.SignInRequestDTO;
import backend.findjob.dto.request.SignUpRequestDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthAPI {

    @Autowired
    private IAuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<ResponeObject> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO)
    {
        return authService.register(signUpRequestDTO);
    }
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
