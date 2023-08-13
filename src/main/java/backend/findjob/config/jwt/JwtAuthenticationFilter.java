package backend.findjob.config.jwt;

import backend.findjob.dto.AuthenticateDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.UserEntity;
import backend.findjob.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private  UserRepository userRepository;
    @Value("${app.jwtAccessSecretKey}")
    private  String jwtAccessSecretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
        {
            try {
                String token = authorizationHeader.substring(7);
//                TokenEntity tokenEntity = tokenRepository.findByToken(token);
//
//                if(tokenEntity.isExpired() == true && tokenEntity.isRevoked() == true)
//                {
//                    response.setHeader("error","Token expired");
//                    response.setStatus(HttpStatus.FORBIDDEN.value());
//                    ResponeObject responeObj = new ResponeObject("FAIL","Token expired","");
//                    response.setContentType(APPLICATION_JSON_VALUE);
//                    new ObjectMapper().writeValue(response.getOutputStream(),responeObj);
//                    return;
//                }

                Algorithm algorithm = Algorithm.HMAC256(jwtAccessSecretKey.getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(token);
                String username = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                Arrays.stream(roles).forEach(role-> {
                    authorities.add(new SimpleGrantedAuthority(role));
                });


                UserEntity user = userRepository.findByUsernameOrEmail(username,username).orElse(null);
                AuthenticateDTO authenticateDTO = new AuthenticateDTO(user.getId(),username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticateDTO,null,authorities);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                filterChain.doFilter(request,response);
            }
            catch (TokenExpiredException e) {
                // Token expired
                response.setHeader("error",e.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                ResponeObject responeObj = new ResponeObject("FAIL",e.getMessage(),"");

                String token = authorizationHeader.substring(7);
//                TokenEntity tokenEntity = tokenRepository.findByToken(token);
//                tokenEntity.setExpired(true);
//                tokenEntity.setRevoked(true);
//                tokenRepository.save(tokenEntity);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),responeObj);

            }
            catch(JWTVerificationException ex)
            {
                response.setHeader("error",ex.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                ResponeObject responeObj = new ResponeObject("FAIL",ex.getMessage(),"");
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),responeObj);
            }
        }
        else{
            filterChain.doFilter(request,response);
        }

    }
}
