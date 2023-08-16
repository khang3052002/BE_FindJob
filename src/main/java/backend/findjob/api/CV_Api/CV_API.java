package backend.findjob.api.CV_Api;

import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.ICVService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/resumes")
@Tag(name ="CV")
public class CV_API {
    @Autowired
    private ICVService cvService;
    @Operation(
            security = @SecurityRequirement(name = "Bearer Auth"),
            requestBody = @RequestBody(
                    content = @Content(mediaType = "multipart/form-data",
                    schemaProperties = @SchemaProperty(name = "file", schema = @Schema(name = "file",format = "binary"))
                    ),
                    description = "Upload CV pdf",
                    required = true
            ),
            responses = {
                    @ApiResponse(
                            description = "Upload CV successful",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            description = "Only users are allowed to upload CV ",
                            responseCode = "401",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping("/upload")
    public ResponseEntity<ResponeObject> uploadCV(
            @RequestParam("file") MultipartFile file,
            @RequestParam("info") String info,
            @RequestParam("id_job") Long id_job
    )
    {

        return cvService.uploadCV(file,info,id_job);
    }
    @Operation(
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(mediaType = "application/pdf")
                    ),
                    @ApiResponse(
                            description = "Not found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/download/{id_resume}")
    public ResponseEntity<?> downloadCV(@PathVariable("id_resume") Long id_resume)
    {
        return cvService.downloadCV(id_resume);
    }




}
