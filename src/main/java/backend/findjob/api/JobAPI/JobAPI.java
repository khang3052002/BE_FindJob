package backend.findjob.api.JobAPI;

import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job")
public class JobAPI {
    @Autowired
    private IJobService jobService;
    @GetMapping("/all")
    public ResponseEntity<ResponeObject> getAllJob()
    {
        //@PathVariable(name="id_user",required = false) Long id_user
//        System.out.println(id_user);
        return jobService.getAll();
    }
    @GetMapping("/{id_job}/detail")
    public ResponseEntity<ResponeObject> getJobById(@PathVariable("id_job") Long id_job)
    {
        System.out.println(id_job);
        return jobService.getJobById(id_job);
    }
}
