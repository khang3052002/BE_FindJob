package backend.findjob.services;

import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.Enum.TypeWork;
import backend.findjob.entity.Enum.TypeWorkPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IJobService {
    public ResponseEntity<ResponeObject> getAll(Pageable page);

    ResponseEntity<ResponeObject> getJobById(Long idJob);

    ResponseEntity<ResponeObject> saveJob(Long idJob);

    ResponseEntity<ResponeObject> unsaveJob(Long idJob);

    ResponseEntity<ResponeObject> getSaveListFromUser(Long idUser);

    public int totalItem();

    ResponseEntity<ResponeObject> getJobByFilter(TypeWorkPlace workplace, TypeWork jobtype, String pos,
                                                 String city, String exp, List<String> specialization,
                                                 Double salary_min, Double salary_max, Pageable pageable);

    ResponseEntity<ResponeObject> searchJobByCityAndKeyword(String keyword, String codeCity, Pageable pageable);
}
