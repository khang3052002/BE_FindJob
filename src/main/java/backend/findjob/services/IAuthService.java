package backend.findjob.services;

import backend.findjob.dto.request.RefreshTokenRequestDTO;
import backend.findjob.dto.request.SignInRequestDTO;
import backend.findjob.dto.request.SignUpRequestDTO;
import backend.findjob.dto.respone.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    public ResponseEntity<ResponeObject> register(SignUpRequestDTO signupRequest);
    public ResponseEntity<ResponeObject> login(SignInRequestDTO signInRequestDTO);

    public ResponseEntity<ResponeObject> refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
}
