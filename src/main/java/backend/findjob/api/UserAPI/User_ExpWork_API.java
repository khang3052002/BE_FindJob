package backend.findjob.api.UserAPI;

import backend.findjob.dto.WorkExpDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/work-exp")
@Tag(name = "User profile work experience")
public class User_ExpWork_API {
    @Autowired
    private IUserService userService;

    @PostMapping("/{id_user}/profile/add-work-exp")
    public ResponseEntity<ResponeObject> addWorkExpByUser(@PathVariable Long id_user, @org.springframework.web.bind.annotation.RequestBody WorkExpDTO workExp)
    {

//        System.out.println(aboutme);
//        System.out.println(aboutme.contains("{"));
        return userService.addWorkExpByUser(id_user, workExp);
    }

    @GetMapping("/{id_user}/profile/detail-work-exp/{id_work_exp}")
    public ResponseEntity<ResponeObject> detailWorkExpById(@PathVariable Long id_user,  @PathVariable Long id_work_exp)
    {

//        System.out.println(aboutme);
//        System.out.println(aboutme.contains("{"));
        return userService.detailWorkExpById(id_user, id_work_exp);
    }
    @DeleteMapping("/{id_user}/profile/delete-work-exp/{id_work_exp}")
    public ResponseEntity<ResponeObject> deleteWorkExpById(@PathVariable Long id_user,  @PathVariable Long id_work_exp)
    {

//        System.out.println(aboutme);
//        System.out.println(aboutme.contains("{"));
        return userService.deleteWorkExpById(id_user, id_work_exp);
    }
    @PutMapping("/{id_user}/profile/update-work-exp/{id_work_exp}")
    public ResponseEntity<ResponeObject>updateWorkExpById(@PathVariable Long id_user,
                                                          @org.springframework.web.bind.annotation.RequestBody WorkExpDTO workExp,
                                                          @PathVariable Long id_work_exp
    )
    {

//        System.out.println(aboutme);
//        System.out.println(aboutme.contains("{"));
        return userService.updateWorkExpById(id_user,id_work_exp, workExp);
    }
}
