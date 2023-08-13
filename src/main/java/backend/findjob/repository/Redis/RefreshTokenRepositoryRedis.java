package backend.findjob.repository.Redis;

import backend.findjob.entity.Redis.RefreshTokenRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepositoryRedis extends CrudRepository<RefreshTokenRedis,String> {

}
