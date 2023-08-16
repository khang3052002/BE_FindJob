package backend.findjob.api.JobAPI;

import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.Enum.TypeWork;
import backend.findjob.entity.Enum.TypeWorkPlace;
import backend.findjob.services.IJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
@Tag(name = "Job")
//@SecurityRequirement(name = "Bearer Auth")
public class JobAPI {
    @Autowired
    private IJobService jobService;
    @Operation(
            security = @SecurityRequirement(name = "Bearer Auth"))
    @GetMapping("/all")
    public ResponseEntity<ResponeObject> getAllJob(@RequestParam("page") Integer page,@RequestParam("limit") Integer limit )
    {
        //@PathVariable(name="id_user",required = false) Long id_user
        System.out.println(page);
        System.out.println(limit);
        page--;
        Pageable pageable = PageRequest.of(page, limit);
        return jobService.getAll(pageable);
    }
    @GetMapping("/{id_job}/detail")
    public ResponseEntity<ResponeObject> getJobById(@PathVariable("id_job") Long id_job)
    {
        System.out.println(id_job);
        return jobService.getJobById(id_job);
    }

    @PostMapping("/{id_job}/save")
    public ResponseEntity<ResponeObject> saveJob(@PathVariable("id_job") Long id_job)
    {
        return jobService.saveJob(id_job);
    }
    @DeleteMapping("/{id_job}/unsave")
    public ResponseEntity<ResponeObject> unsaveJob(@PathVariable("id_job") Long id_job)
    {

        return jobService.unsaveJob(id_job);

    }
    @GetMapping("/{id_user}/savelistuser")
    public ResponseEntity<ResponeObject> getSaveListFromUser(@PathVariable("id_user") Long id_user)
    {
        return jobService.getSaveListFromUser(id_user);
    }

    @GetMapping("")
    public ResponseEntity<ResponeObject> getJobByFilter(
            @RequestParam(name = "workplace", required = false) TypeWorkPlace workplace,
            @RequestParam(name = "jobtype", required = false) TypeWork jobtype,
            @RequestParam(name = "pos", required = false)  String pos,
            @RequestParam(name = "city",required = false) String city,
            @RequestParam(name="exp", required = false) String exp,
            @RequestParam(name="specialization", required = false) List<String> specialization,
            @RequestParam(name="salary_min", required = false) Double salary_min,
            @RequestParam(name="salary_max", required = false) Double salary_max

            )
    {
//        System.out.println(specialization.get(0));
//        System.out.println(specialization.toString());
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new ResponeObject("Success", "Get all job list successfull", "listJobDTO"));

        return jobService.getJobByFilter(workplace,jobtype, pos,city,exp,specialization,salary_min,salary_max);
    }
}
