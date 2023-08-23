package backend.findjob.services;

import backend.findjob.dto.EducationDTO;
import backend.findjob.dto.WorkExpDTO;
import backend.findjob.dto.respone.ResponeObject;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
    public ResponseEntity<ResponeObject> uploadAvatar(@RequestParam("image") MultipartFile image);

//    ResponseEntity<Resource> getAvatar(Long idImage);

    ResponseEntity<ResponeObject> editAboutMeByUser(Long idUser, String aboutme);

    ResponseEntity<ResponeObject> addWorkExpByUser(Long idUser, WorkExpDTO workExp);

    ResponseEntity<ResponeObject> detailWorkExpById(Long idUser, Long idWorkExp);

    ResponseEntity<ResponeObject> deleteWorkExpById(Long idUser, Long idWorkExp);

    ResponseEntity<ResponeObject> updateWorkExpById(Long idUser, Long id_work_exp, WorkExpDTO workExp);

    ResponseEntity<ResponeObject> addEducationByUser(Long idUser, EducationDTO educationDTO);

    ResponseEntity<ResponeObject> detailEducationById(Long idUser, Long idEducation);

    ResponseEntity<ResponeObject> deleteEducationById(Long idUser, Long idEducation);

    ResponseEntity<ResponeObject> updateEducationById(Long idUser, Long idEducation, EducationDTO educationDTO);

    ResponseEntity<ResponeObject> uploadImage(MultipartFile image);
}
