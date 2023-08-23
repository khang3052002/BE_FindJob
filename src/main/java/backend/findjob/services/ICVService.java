package backend.findjob.services;

import backend.findjob.dto.respone.ResponeObject;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface ICVService {
    public ResponseEntity<ResponeObject> uploadCV(MultipartFile file, String info, Long id_job);

//    ResponseEntity<Resource> downloadCV(Long idResume);
}
