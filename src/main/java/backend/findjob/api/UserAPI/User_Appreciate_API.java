package backend.findjob.api.UserAPI;

import backend.findjob.dto.AppreciateDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/appreciate")
@Tag(name = "User profile appreciate")
public class User_Appreciate_API {
    @Autowired
    private IUserService userService;
    @PostMapping("/{id_user}/profile/add-appreciate")
    public ResponseEntity<ResponeObject> addAppreciateByUser(@PathVariable Long id_user, @RequestBody @Valid AppreciateDTO appreciateDTO)
    {

        return userService.addAppreciateByUser(id_user, appreciateDTO);
    }

    @GetMapping("/{id_user}/profile/detail-appreciate/{id_appreciate}")
    public ResponseEntity<ResponeObject> detailAppreciateById(@PathVariable Long id_user,  @PathVariable Long id_appreciate)
    {
        return userService.detailAppreciateById(id_user, id_appreciate);
    }
    @DeleteMapping("/{id_user}/profile/delete-appreciate/{id_appreciate}")
    public ResponseEntity<ResponeObject> deleteAppreciateById(@PathVariable Long id_user,  @PathVariable Long id_appreciate)
    {
        return userService.deleteAppreciateById(id_user, id_appreciate);
    }
    @PutMapping("/{id_user}/profile/update-appreciate/{id_appreciate}")
    public ResponseEntity<ResponeObject>updateAppreciateById(@PathVariable Long id_user,
                                                            @RequestBody @Valid AppreciateDTO appreciateDTO,
                                                            @PathVariable Long id_appreciate
    )
    {
        return userService.updateAppreciateById(id_user,id_appreciate, appreciateDTO);
    }
}
