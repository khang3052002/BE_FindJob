package backend.findjob.services;

import backend.findjob.dto.respone.ResponeObject;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    public ResponseEntity<ResponeObject> uploadAvatar(@RequestParam("image") MultipartFile image);

    ResponseEntity<Resource> getAvatar(Long idImage);
}
