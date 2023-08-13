package backend.findjob.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Value("${app.passwordRedis}")
    private String passwordRedis;
    @Value("${app.portRedis}")
    private int portRedis;
    @Value("${app.hostRedis}")
    private String hostRedis;
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        System.out.println("da vao redis");
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        jedisConFactory.setHostName(hostRedis);
        jedisConFactory.setPort(portRedis);
        jedisConFactory.setPassword(passwordRedis);
        return jedisConFactory;
    }
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        // value serialization
        template.setValueSerializer(new StringRedisSerializer());
        // Hash key serialization
        template.setHashKeySerializer(new StringRedisSerializer());
        // Hash value serialization
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }

}
