package backend.findjob.services.impls;

import backend.findjob.dto.AuthenticateDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.CVEntity;
import backend.findjob.entity.ImageUserEntity;
import backend.findjob.entity.JobEntity;
import backend.findjob.entity.UserEntity;
import backend.findjob.repository.ImageUserRepository;
import backend.findjob.repository.UserRepository;
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
    private ImageUserRepository imageUserRepository;
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
            ImageUserEntity oldImage = user.getImageUser();

            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            String url = "";
            try {
                if(fileName.contains("..")) {
                    return ResponseEntity
                            .status(HttpStatus.FORBIDDEN)
                            .body(new ResponeObject("Fail","Filename contains invalid path sequence",""));
                }

                ImageUserEntity newImage = new ImageUserEntity();
                newImage.setImage_data(image.getBytes());
                newImage.setCreate_at(new Timestamp(System.currentTimeMillis()));
                newImage.setType(image.getContentType());
//                imageUserEntity.setUser(user);



                newImage = imageUserRepository.save(newImage);
                user.setImageUser(newImage);
                userRepository.save(user);

                url = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/user/image/get_avatar/")
                        .path(newImage.getId().toString())
                        .toUriString();
                newImage.setUrl(url);
                imageUserRepository.save(newImage);

                // delete old image form db
                if(oldImage != null)
                {
                    imageUserRepository.delete(oldImage);
                }

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponeObject("Fail",e.getMessage(),""));

            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Upload avatar successful",url));

        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to upload avatar ",""));
        }
    }

    @Override
    public ResponseEntity<Resource> getAvatar(Long idImage) {
        try
        {
            ImageUserEntity image = imageUserRepository.findById(idImage).orElse(null);
            return  ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getType()))

                    .body(new ByteArrayResource(image.getImage_data()));

            // If you want to dowload resource, uncomment it
            // .header(HttpHeaders.CONTENT_DISPOSITION,
            // "attachment; filename=\"" + cv.getName()
            //   + "\"")
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

}
