package backend.findjob.api.UserAPI;

import backend.findjob.dto.WorkExpDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.IUserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


@RestController
@RequestMapping("/api/user")
@Tag(name = "User")
public class UserAPI {
    @Autowired
    private IUserService userService;
    @Operation(
            security = @SecurityRequirement(name = "Bearer Auth"),
            requestBody = @RequestBody(
                    content = @Content(mediaType = "multipart/form-data",
                            schemaProperties = @SchemaProperty(name = "image", schema = @Schema(name = "image",format = "binary"))
                    ),
                    description = "Upload avatar image",
                    required = true
            ),
            responses = {
                    @ApiResponse(
                            description = "Upload avatar successful",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            description = "Only users are allowed to upload avatar ",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping("/image/upload_avatar")
    public ResponseEntity<ResponeObject> uploadAvatar(@RequestParam("image") MultipartFile image)
    {
        return userService.uploadAvatar(image);
    }

//    @GetMapping("/image/get_avatar/{id_image}")
//    public ResponseEntity<Resource> getAvatar(@PathVariable("id_image") Long id_image)
//    {
//        return userService.getAvatar(id_image);
//    }

    @Operation(
            security = @SecurityRequirement(name = "Bearer Auth")
    )
    @PutMapping("/{id_user}/profile/edit-about-me")
    public ResponseEntity<ResponeObject> editAboutMeByUser(@PathVariable Long id_user, @RequestParam("aboutme") String aboutme)
    {

//        System.out.println(aboutme);
//        System.out.println(aboutme.contains("{"));
        return userService.editAboutMeByUser(id_user, aboutme);
    }
    @PostMapping("/upload-image")
    @Hidden
    public ResponseEntity<ResponeObject> uploadImage(@RequestParam("image") MultipartFile image)
    {
        return userService.uploadImage(image);
    }
}
