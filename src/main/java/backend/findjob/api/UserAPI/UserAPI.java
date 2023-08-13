package backend.findjob.api.UserAPI;

import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


@RestController
@RequestMapping("/api/user")
public class UserAPI {
    @Autowired
    private IUserService userService;
    @PostMapping("/image/upload_avatar")
    public ResponseEntity<ResponeObject> uploadAvatar(@RequestParam("image")MultipartFile image)
    {
        return userService.uploadAvatar(image);
    }

    @GetMapping("/image/get_avatar/{id_image}")
    public ResponseEntity<Resource> getAvatar(@PathVariable("id_image") Long id_image)
    {
        return userService.getAvatar(id_image);
    }

}
