package backend.findjob.services.impls;

import backend.findjob.config.jwt.JwtService;
import backend.findjob.dto.request.RefreshTokenRequest;
import backend.findjob.dto.request.SignInRequest;
import backend.findjob.dto.request.SignUpRequest;
import backend.findjob.dto.respone.InfoUserResponeDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.UserEntity;
import backend.findjob.repository.UserRepository;
import backend.findjob.services.IAuthService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtTokenProvider;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${app.jwtRefreshSecretKey}")
    private  String jwtRefreshSecretKey;
    @Override
    public ResponseEntity<ResponeObject> register(SignUpRequest signupRequest) {
        Boolean usernameExists = userRepository.existsByUsername(signupRequest.getUsername());
        Boolean emailExists = userRepository.existsByEmail(signupRequest.getEmail());

        if(usernameExists == true)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("conflict","Username is already taken.",""));

        }
        if(emailExists == true)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponeObject("conflict","Email is already taken.",""));

        }

        // Username and email not exsists
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signupRequest.getEmail());
        userEntity.setUsername(signupRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userEntity.setName(signupRequest.getName());

        userRepository.save(userEntity);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("OK","Register successful",signupRequest));

    }

    @Override
    public ResponseEntity<ResponeObject> login(SignInRequest signInRequestDTO) {
        Authentication authentication= null;
        String usernameOrEmail = "";
        if(signInRequestDTO.getUsername() != null)
        {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequestDTO.getUsername(),signInRequestDTO.getPassword()
                            ));
            usernameOrEmail = signInRequestDTO.getUsername();
        }
        if(signInRequestDTO.getEmail() != null)
        {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequestDTO.getEmail(),signInRequestDTO.getPassword()
                    ));
            usernameOrEmail = signInRequestDTO.getEmail();

        }
       if(authentication.isAuthenticated())
       {
            String accessToken = jwtTokenProvider.generateAccessToken(usernameOrEmail);
            String refreshToken = jwtTokenProvider.generateRefreshToken(usernameOrEmail);

           // account co the la email or username
           UserEntity userEntity = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail).orElse(null);
           if(userEntity != null)
           {
               InfoUserResponeDTO infoUserResponeDTO = new InfoUserResponeDTO(
                       userEntity.getId(),userEntity.getUsername(),userEntity.getEmail(),
                       userEntity.getName(),userEntity.getUrl_avatar(),accessToken,refreshToken
               );
               String key  = "user"+userEntity.getId().toString() + userEntity.getUsername();
//               redisTemplate.opsForValue().set(key,refreshToken);
               return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK","Authenticated Success",infoUserResponeDTO));
           }
       }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Authenticated Fail",""));

    }

    @Override
    public ResponseEntity<ResponeObject> refreshToken(RefreshTokenRequest refreshTokenRequestDTO) {
        try
        {
            String rfToken = refreshTokenRequestDTO.getRefreshToken();
            String rfTokenRedis =(String) redisTemplate.opsForValue().get("user"+refreshTokenRequestDTO.getId().toString()+refreshTokenRequestDTO.getUsername());

            if(rfToken.equals(rfTokenRedis))
            {

                Algorithm algorithm = Algorithm.HMAC256(jwtRefreshSecretKey.getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(rfToken);
                String usernameOrEmail = decodedJWT.getSubject();
                String accessToken = jwtTokenProvider.generateAccessToken(usernameOrEmail);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponeObject("OK","Refresh token successful",accessToken));
            }
        }
        catch (TokenExpiredException e)
        {
            // Token expired
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponeObject("For bidden",e.getMessage(),""));

        }
        catch(JWTVerificationException e)
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponeObject("For bidden",e.getMessage(),""));

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponeObject("Error","Error in BE",""));




    }
}
