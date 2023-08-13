package backend.findjob.services;

import backend.findjob.dto.respone.ResponeObject;
import org.springframework.http.ResponseEntity;

public interface IJobService {
    public ResponseEntity<ResponeObject> getAll();

    ResponseEntity<ResponeObject> getJobById(Long idJob);
}
