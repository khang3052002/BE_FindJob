package backend.findjob.services.impls;

import backend.findjob.dto.AuthenticateDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.CVEntity;
import backend.findjob.entity.JobEntity;
import backend.findjob.entity.UserEntity;
import backend.findjob.repository.CVRepository;
import backend.findjob.repository.JobRepository;
import backend.findjob.repository.UserRepository;
import backend.findjob.services.ICVService;
import backend.findjob.services.IUploadFileService;
import com.cloudinary.Cloudinary;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
public class CVService implements ICVService {
    @Autowired
    private CVRepository cvRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUploadFileService uploadFileService;

    @Override
    public ResponseEntity<ResponeObject> uploadCV(MultipartFile file, String info, Long id_job) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class)
        {
            authenticateDTO = (AuthenticateDTO) authentication.getPrincipal();

            JobEntity job = jobRepository.findById(id_job).orElse(null);
            UserEntity user = userRepository.findById(authenticateDTO.getId()).orElse(null);
            if(job == null)
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Job not exists",""));

            }
            if(user == null)
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","User not exists",""));
            }

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                if(fileName.contains("..")) {
                    return ResponseEntity
                            .status(HttpStatus.FORBIDDEN)
                            .body(new ResponeObject("Fail","Filename contains invalid path sequence",""));
                }

                // Upload cv to cloudinary
                String url = uploadFileService.uploadFile(file);
                System.out.println(url);
                CVEntity cv = new CVEntity();
                cv.setName(fileName);
                cv.setUrl(url);
//                cv.setType(file.getContentType());
//                cv.setCv_data(file.getBytes());
                cv.setCreate_at(new Timestamp(System.currentTimeMillis()));
                cv.setInfo(info);
                cv.setUser(user);
                cv.setJob(job);

                cv =  cvRepository.save(cv);

//                String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                        .path("/api/resumes/download/")
//                        .path(cv.getId().toString())
//                        .toUriString();
//
//                System.out.println(downloadURl);

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                        .body(new ResponeObject("Fail",e.getMessage(),""));

            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Upload CV successful",""));

        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponeObject("Unauthorized","Only users are allowed to upload CV ",""));
        }
    }

//    @Override
//    public ResponseEntity<Resource> downloadCV(Long idResume) {
//        try
//        {
//            CVEntity cv = cvRepository.findById(idResume).orElse(null);
//            if(cv == null)
//            {
//                return  ResponseEntity.notFound()
//                        .build();
//            }
//            return  ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(cv.getType()))
//
//                    .body(new ByteArrayResource(cv.getCv_data()));
//
//                // If you want to dowload resource, uncomment it
//                // .header(HttpHeaders.CONTENT_DISPOSITION,
//                // "attachment; filename=\"" + cv.getName()
//                //   + "\"")
//                }
//        catch (Exception ex)
//        {
//            throw ex;
//        }
//
//    }
}
