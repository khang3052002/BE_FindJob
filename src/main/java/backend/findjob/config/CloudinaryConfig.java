package backend.findjob.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary ()
    {
//        Cloudinary cloudinary = new Cloudinary(ObjectUtils
//                .asMap("cloud_name", "dson4gzwa",
//            "api_key", "253499245834535",
//            "api_secret", "oCLKWC-ME9JZU7g2vLQMUF8FtEw"));

        Map<String, String> config = new HashMap<>();
        config.put("cloud_name","dson4gzwa");
        config.put("api_key","253499245834535");
        config.put("api_secret","oCLKWC-ME9JZU7g2vLQMUF8FtEw");


        return new Cloudinary(config);
    }
}
