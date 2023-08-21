package backend.findjob.services;

import backend.findjob.dto.request.RefreshTokenRequest;
import backend.findjob.dto.request.SignInRequest;
import backend.findjob.dto.request.SignUpRequest;
import backend.findjob.dto.respone.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    public ResponseEntity<ResponeObject> register(SignUpRequest signupRequest);
    public ResponseEntity<ResponeObject> login(SignInRequest signInRequestDTO);

    public ResponseEntity<ResponeObject> refreshToken(RefreshTokenRequest refreshTokenRequestDTO);
}
