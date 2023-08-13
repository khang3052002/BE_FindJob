package backend.findjob.api.CV_Api;

import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.ICVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/resumes")
public class CV_API {
    @Autowired
    private ICVService cvService;
    @PostMapping("/upload")
    public ResponseEntity<ResponeObject> uploadCV(
            @RequestParam("file") MultipartFile file,
            @RequestParam("info") String info,
            @RequestParam("id_job") Long id_job
    )
    {

        return cvService.uploadCV(file,info,id_job);
    }
    @GetMapping("/download/{id_resume}")
    public ResponseEntity<Resource> downloadCV(@PathVariable("id_resume") Long id_resume)
    {
        return cvService.downloadCV(id_resume);
    }




}
