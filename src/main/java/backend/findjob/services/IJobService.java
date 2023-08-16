package backend.findjob.services;

import backend.findjob.dto.respone.ResponeObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IJobService {
    public ResponseEntity<ResponeObject> getAll(Pageable page);

    ResponseEntity<ResponeObject> getJobById(Long idJob);

    ResponseEntity<ResponeObject> saveJob(Long idJob);

    ResponseEntity<ResponeObject> unsaveJob(Long idJob);

    ResponseEntity<ResponeObject> getSaveListFromUser(Long idUser);

    public int totalItem();
}
