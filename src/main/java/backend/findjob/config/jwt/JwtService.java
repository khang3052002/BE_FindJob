package backend.findjob.config.jwt;

import backend.findjob.entity.UserEntity;
import backend.findjob.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.sql.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Autowired
    private UserRepository userRepository;

    @Value("${app.jwtAccessSecretKey}")
    private  String jwtAccessSecretKey;
    @Value("${app.AccessTokenExp}")
    private int AccessTokenExp;

    @Value("${app.jwtRefreshSecretKey}")
    private String jwtRefreshSecretKey;
    @Value("${app.RefreshTokenExp}")
    private int RefreshTokenExp;



    public String generateAccessToken(String username)
    {
        UserEntity user = userRepository.findByUsernameOrEmail(username,username).get();
        Collection<SimpleGrantedAuthority> authorities  = (Collection<SimpleGrantedAuthority>) user.getAuthorities();
        Date expiryDate = new Date(System.currentTimeMillis() + AccessTokenExp);

        Algorithm algorithm = Algorithm.HMAC256(jwtAccessSecretKey.getBytes());

        return JWT.create()
                .withClaim("roles",authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()) )
                .withSubject(username)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(expiryDate)
                .sign(algorithm);

    }

    public String generateRefreshToken(String username)
    {
        UserEntity user = userRepository.findByUsernameOrEmail(username,username).get();
        Collection<SimpleGrantedAuthority> authorities  = (Collection<SimpleGrantedAuthority>) user.getAuthorities();
        Date expiryDate = new Date(System.currentTimeMillis() + RefreshTokenExp);

        Algorithm algorithm = Algorithm.HMAC256(jwtRefreshSecretKey.getBytes());

        return JWT.create()
                .withClaim("roles",authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()) )
                .withSubject(username)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(expiryDate)
                .sign(algorithm);

    }


}
