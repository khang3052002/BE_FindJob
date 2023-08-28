package backend.findjob.api.UserAPI;

import backend.findjob.dto.EducationDTO;
import backend.findjob.dto.WorkExpDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/education")
@Tag(name = "User profile education")
public class User_Education_API {
    @Autowired
    private IUserService userService;

    @PostMapping("/{id_user}/profile/add-education")
    public ResponseEntity<ResponeObject> addEducationByUser(@PathVariable Long id_user, @RequestBody @Valid EducationDTO educationDTO)
    {

        return userService.addEducationByUser(id_user, educationDTO);
    }

    @GetMapping("/{id_user}/profile/detail-education/{id_education}")
    public ResponseEntity<ResponeObject> detailEducationById(@PathVariable Long id_user,  @PathVariable Long id_education)
    {
        return userService.detailEducationById(id_user, id_education);
    }
    @DeleteMapping("/{id_user}/profile/delete-education/{id_education}")
    public ResponseEntity<ResponeObject> deleteEducationById(@PathVariable Long id_user,  @PathVariable Long id_education)
    {
        return userService.deleteEducationById(id_user, id_education);
    }
    @PutMapping("/{id_user}/profile/update-education/{id_education}")
    public ResponseEntity<ResponeObject>updateEducationById(@PathVariable Long id_user,
                                                          @RequestBody @Valid EducationDTO educationDTO,
                                                          @PathVariable Long id_education
    )
    {
        return userService.updateEducationById(id_user,id_education, educationDTO);
    }
}
