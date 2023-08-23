package backend.findjob.services.impls;

import backend.findjob.dto.AppreciateDTO;
import backend.findjob.dto.AuthenticateDTO;
import backend.findjob.dto.EducationDTO;
import backend.findjob.dto.WorkExpDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.*;
import backend.findjob.helper.GenericConverter;
import backend.findjob.repository.AppreciateRepository;
import backend.findjob.repository.EducationRepository;
import backend.findjob.repository.UserRepository;
import backend.findjob.repository.WorkExpRepository;
import backend.findjob.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.sql.Timestamp;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkExpRepository workExpRepository;

    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private AppreciateRepository appreciateRepository;
    @Override
    public ResponseEntity<ResponeObject> uploadAvatar(MultipartFile image) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class)
        {
            authenticateDTO = (AuthenticateDTO) authentication.getPrincipal();

            UserEntity user = userRepository.findById(authenticateDTO.getId()).orElse(null);

            if(user == null)
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","User not exists",""));
            }
            // save old image before delete

            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            String url = "";
            try {
                if(fileName.contains("..")) {
                    return ResponseEntity
                            .status(HttpStatus.FORBIDDEN)
                            .body(new ResponeObject("Fail","Filename contains invalid path sequence",""));
                }

//                imageUserEntity.setUser(user);

                String url_avatar = uploadFileService.uploadFile(image);
                user.setUrl_avatar(url_avatar);
                userRepository.save(user);

//                url = ServletUriComponentsBuilder.fromCurrentContextPath()
//                        .path("/api/user/image/get_avatar/")
//                        .path(newImage.getId().toString())
//                        .toUriString();
//                newImage.setUrl(url);
//                imageUserRepository.save(newImage);
//
//                // delete old image form db
//                if(oldImage != null)
//                {
//                    imageUserRepository.delete(oldImage);
//                }

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeObject("Fail",e.getMessage(),""));

            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Upload avatar successful",url));

        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to upload avatar ",""));
        }
    }

//    @Override
//    public ResponseEntity<Resource> getAvatar(Long idImage) {
//        try
//        {
//            ImageUserEntity image = imageUserRepository.findById(idImage).orElse(null);
//            return  ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(image.getType()))
//
//                    .body(new ByteArrayResource(image.getImage_data()));
//
//            // If you want to dowload resource, uncomment it
//            // .header(HttpHeaders.CONTENT_DISPOSITION,
//            // "attachment; filename=\"" + cv.getName()
//            //   + "\"")
//        }
//        catch (Exception ex)
//        {
//            throw ex;
//        }
//    }

    @Override
    public ResponseEntity<ResponeObject> editAboutMeByUser(Long idUser, String aboutme) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            authenticateDTO = (AuthenticateDTO) authentication.getPrincipal();

            UserEntity user = userRepository.findById(idUser).orElse(null);

            if (user == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found", "User not exists", ""));
            }

            user.setAbout_me(aboutme);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Edit successfull",""));

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to edit profile ",""));

    }

    @Override
    public ResponseEntity<ResponeObject> addWorkExpByUser(Long idUser, WorkExpDTO workExp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            authenticateDTO = (AuthenticateDTO) authentication.getPrincipal();

            UserEntity user = userRepository.findById(idUser).orElse(null);

            if (user == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found", "User not exists", ""));
            }

            WorkExpEntity workExpEntity = new WorkExpEntity();
            workExpEntity.setCompany(workExp.getCompany());
            workExpEntity.setDescription(workExp.getDescription());
            workExpEntity.setTitle(workExp.getTitle());
            workExpEntity.setPositionNow(workExp.getIs_position_now());
            workExpEntity.setStart_date(workExp.getStart_date());
            workExpEntity.setEnd_date(workExp.getEnd_date());


            workExpEntity.setUser(user);
            workExpRepository.save(workExpEntity);




            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Add work experience successful",""));

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to add work experience",""));

    }

    @Override
    public ResponseEntity<ResponeObject> detailWorkExpById(Long idUser, Long idWorkExp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            WorkExpEntity workExpEntity = workExpRepository.findById(idWorkExp).orElse(null);
            if(workExpEntity == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found",""));
            }
            if(workExpEntity.getUser().getId() != idUser)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found work experience by user",""));
            }
            try
            {
                WorkExpDTO workExpDTO = GenericConverter.convert(workExpEntity,WorkExpDTO.class);

                return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Get detail work experience successful",workExpDTO));

            }catch (Exception ex)
            {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeObject("Exception",ex.getMessage(),""));

            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to add work experience",""));


    }

    @Override
    public ResponseEntity<ResponeObject> deleteWorkExpById(Long idUser, Long idWorkExp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            WorkExpEntity workExpEntity = workExpRepository.findById(idWorkExp).orElse(null);
            if(workExpEntity == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found",""));
            }
            if(workExpEntity.getUser().getId() != idUser)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found work experience by user",""));
            }

            workExpRepository.delete(workExpEntity);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponeObject("Success","Delete work experience successful",""));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to add work experience",""));


    }

    @Override
    public ResponseEntity<ResponeObject> updateWorkExpById(Long idUser,  Long id_work_exp, WorkExpDTO workExp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            WorkExpEntity workExpEntity = workExpRepository.findById(id_work_exp).orElse(null);
            if(workExpEntity == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found",""));
            }
            if(workExpEntity.getUser().getId() != idUser)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found work experience by user",""));
            }
            workExpEntity.setCompany(workExp.getCompany());
            workExpEntity.setDescription(workExp.getDescription());
            workExpEntity.setTitle(workExp.getTitle());
            workExpEntity.setPositionNow(workExp.getIs_position_now());
            workExpEntity.setStart_date(workExp.getStart_date());
            workExpEntity.setEnd_date(workExp.getEnd_date());

            workExpRepository.save(workExpEntity);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponeObject("Success","Update work experience successful",""));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to update work experience",""));


    }

    @Override
    public ResponseEntity<ResponeObject> addEducationByUser(Long idUser, EducationDTO educationDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            authenticateDTO = (AuthenticateDTO) authentication.getPrincipal();

            UserEntity user = userRepository.findById(idUser).orElse(null);

            if (user == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found", "User not exists", ""));
            }

            EducationEntity educationEntity = new EducationEntity();
            educationEntity.setSchool(educationDTO.getSchool());
            educationEntity.setDescription(educationDTO.getDescription());
            educationEntity.setTitle(educationDTO.getTitle());
            educationEntity.setStart_date(educationDTO.getStart_date());
            educationEntity.setEnd_date(educationDTO.getEnd_date());


            educationEntity.setUser(user);
            educationRepository.save(educationEntity);




            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Add education successful",""));

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to add education.",""));

    }

    @Override
    public ResponseEntity<ResponeObject> detailEducationById(Long idUser, Long idEducation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            EducationEntity educationEntity = educationRepository.findById(idEducation).orElse(null);
            if(educationEntity == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found",""));
            }
            if(educationEntity.getUser().getId() != idUser)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found work experience by user",""));
            }
            try
            {
                EducationDTO educationDTO = GenericConverter.convert(educationEntity,EducationDTO.class);

                return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Get detail education successful",educationDTO));

            }catch (Exception ex)
            {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeObject("Exception",ex.getMessage(),""));

            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to get detail education",""));

    }

    @Override
    public ResponseEntity<ResponeObject> deleteEducationById(Long idUser, Long idEducation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            EducationEntity educationEntity = educationRepository.findById(idEducation).orElse(null);
            if(educationEntity == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found",""));
            }
            if(educationEntity.getUser().getId() != idUser)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found work experience by user",""));
            }

            educationRepository.delete(educationEntity);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponeObject("Success","Delete education successful",""));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to delete education",""));


    }

    @Override
    public ResponseEntity<ResponeObject> updateEducationById(Long idUser, Long idEducation, EducationDTO educationDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            EducationEntity educationEntity = educationRepository.findById(idEducation).orElse(null);
            if(educationEntity == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found",""));
            }
            if(educationEntity.getUser().getId() != idUser)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found work experience by user",""));
            }
            educationEntity.setDescription(educationDTO.getDescription());
            educationEntity.setSchool(educationDTO.getSchool());
            educationEntity.setTitle(educationDTO.getTitle());
            educationEntity.setStart_date(educationDTO.getStart_date());
            educationEntity.setEnd_date(educationDTO.getEnd_date());

            educationRepository.save(educationEntity);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponeObject("Success","Update education successful",""));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to update education",""));

    }

    @Override
    public ResponseEntity<ResponeObject> uploadImage(MultipartFile image) {
        if(image.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponeObject("Not content","Not content",""));

        }
        String urlFile = uploadFileService.uploadFile(image);
        System.out.println(urlFile);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("Successful","Upload successful",urlFile));

    }

    @Override
    public ResponseEntity<ResponeObject> addAppreciateByUser(Long idUser, AppreciateDTO appreciateDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            authenticateDTO = (AuthenticateDTO) authentication.getPrincipal();

            UserEntity user = userRepository.findById(idUser).orElse(null);

            if (user == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found", "User not exists", ""));
            }

            AppreciateEntity appreciateEntity = new AppreciateEntity();
            appreciateEntity.setTitle(appreciateDTO.getTitle());
            appreciateEntity.setPosition(appreciateDTO.getPosition());
            appreciateEntity.setYear(appreciateDTO.getYear());
            appreciateEntity.setUser(user);
            appreciateRepository.save(appreciateEntity);




            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Add appreciate successful",""));

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to add appreciate.",""));

    }

    @Override
    public ResponseEntity<ResponeObject> detailAppreciateById(Long idUser, Long idAppreciate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            AppreciateEntity appreciateEntity = appreciateRepository.findById(idAppreciate).orElse(null);
            if(appreciateEntity == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found",""));
            }
            if(appreciateEntity.getUser().getId() != idUser)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found work experience by user",""));
            }
            try
            {
                AppreciateDTO appreciateDTO = GenericConverter.convert(appreciateEntity,AppreciateDTO.class);

                return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Get detail appreciate successful",appreciateDTO));

            }catch (Exception ex)
            {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeObject("Exception",ex.getMessage(),""));

            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to get detail appreciateDTO",""));

    }

    @Override
    public ResponseEntity<ResponeObject> deleteAppreciateById(Long idUser, Long idAppreciate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            AppreciateEntity appreciateEntity = appreciateRepository.findById(idAppreciate).orElse(null);
            if(appreciateEntity == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found",""));
            }
            if(appreciateEntity.getUser().getId() != idUser)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found appreciate by user",""));
            }

            appreciateRepository.delete(appreciateEntity);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponeObject("Success","Delete appreciate successful",""));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to delete appreciate",""));

    }

    @Override
    public ResponseEntity<ResponeObject> updateAppreciateById(Long idUser, Long idAppreciate, AppreciateDTO appreciateDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class) {
            AppreciateEntity appreciateEntity = appreciateRepository.findById(idAppreciate).orElse(null);
            if(appreciateEntity == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found",""));
            }
            if(appreciateEntity.getUser().getId() != idUser)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Not found appreciate by user",""));
            }
            appreciateEntity.setTitle(appreciateDTO.getTitle());
            appreciateEntity.setPosition(appreciateDTO.getPosition());
            appreciateEntity.setYear(appreciateDTO.getYear());
            appreciateRepository.save(appreciateEntity);


            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponeObject("Success","Update appreciate successful",""));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to update appreciate",""));

    }


}
