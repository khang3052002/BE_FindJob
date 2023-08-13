package backend.findjob.api;

import backend.findjob.dto.TestDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.Redis.RefreshTokenRedis;
import backend.findjob.repository.Redis.RefreshTokenRepositoryRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/test")
public class testApi {
    @Autowired
    private RefreshTokenRepositoryRedis refreshTokenRepositoryRedis;
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("/")
    public ResponseEntity<ResponeObject> test1()
    {
        String rfToken = (String) redisTemplate.opsForValue().get("user1khang305");
        if( rfToken!= null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("thanh cong","OK",rfToken));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponeObject("THAT BAI","FAIL",null));

    }

    @PostMapping("/save")
    public ResponseEntity<ResponeObject> test2(@RequestBody TestDTO testDTO)
    {
        System.out.println("haha"+ testDTO.getPassword());

        System.out.println("haha"+ testDTO.getUsername());

        redisTemplate.opsForValue().set(testDTO.getUsername(), testDTO.getPassword());

//        RefreshTokenRedis refreshTokenRedis = new RefreshTokenRedis();
//        refreshTokenRedis.setId(testDTO.getUsername());
//        refreshTokenRedis.setRefreshToken(testDTO.getPassword());
//        refreshTokenRepositoryRedis.save(refreshTokenRedis);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("FAIL","FAIL HAHA",""));

    }
}
