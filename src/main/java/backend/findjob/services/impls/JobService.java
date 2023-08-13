package backend.findjob.services.impls;

import backend.findjob.dto.AuthenticateDTO;
import backend.findjob.dto.respone.Company.CompanyDTO;
import backend.findjob.dto.respone.Job.JobDTO;
import backend.findjob.dto.respone.Job.JobDetailDTO;
import backend.findjob.dto.respone.Job.JobDetailRespone;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.JobEntity;
import backend.findjob.entity.UserEntity;
import backend.findjob.helper.GenericConverter;
import backend.findjob.repository.JobRepository;
import backend.findjob.repository.UserRepository;
import backend.findjob.services.ICompanyService;
import backend.findjob.services.IJobService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JobService implements IJobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ICompanyService companyService;

    @Override
    public ResponseEntity<ResponeObject> getAll() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;
        AuthenticateDTO authenticateDTO  = null;
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class)
        {
            authenticateDTO = (AuthenticateDTO) authentication.getPrincipal();
            username = authenticateDTO.getUsername();
        }
        else{
           username = (String) authentication.getPrincipal();
        }
        System.out.println(username);
        Boolean is_user = false;
        Long id_user = null;
        if (!username.equals("anonymousUser")) {
            is_user = true;
            id_user = authenticateDTO.getId();
        }

        List<JobEntity> listJob = jobRepository.findAll();


        List<JobDTO> listJobDTO = new ArrayList<>();
        try {
            for (JobEntity jobEntity : listJob) {
                JobDTO job = GenericConverter.convert(jobEntity, JobDTO.class);
                job.setTime_create(jobEntity.getCreate_at());
                job.setNameCompany(jobEntity.getCompany().getName());
                job.setId_company(jobEntity.getCompany().getId());
                job.getTags().add(jobEntity.getType_work().toString());
                job.getTags().add(jobEntity.getLocation());
                job.getTags().add(jobEntity.getExperience());

                // check is save
                if (is_user == false) {
                    job.setIs_saved(false);
                } else {
                    List<UserEntity> listUserSave = jobEntity.getListUser();
                    if (listUserSave.isEmpty()) {
                        job.setIs_saved(false);
                    } else {
                        job.setIs_saved(false);
                        for (UserEntity user : listUserSave) {
                            if (user.getId().equals(id_user)) {
                                job.setIs_saved(true);
                                break;
                            }
                        }

                    }

                }
                listJobDTO.add(job);

            }
        } catch (Exception e) {
//            throw new RuntimeException(e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponeObject("Fail", e.getMessage(), ""));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("Success", "Get all job list successfull", listJobDTO));
    }
    @Override
    public ResponseEntity<ResponeObject> getJobById(Long idJob) {
        JobEntity jobEntity = jobRepository.findById(idJob).orElse(null);
        if(jobEntity == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponeObject("Not found","Job not exists", ""));
        }

        JobDetailDTO jobDetailDTO = convertJobEntityToDTO(jobEntity);
        CompanyDTO companyDTO = companyService.convertCompanyEntityToDTO(jobEntity.getCompany());

        JobDetailRespone jobDetailRespone = new JobDetailRespone(jobDetailDTO,companyDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("Success", "Get job successfull", jobDetailRespone));


    }

    public JobDetailDTO convertJobEntityToDTO(JobEntity jobEntity)
    {
        try
        {
            JobDetailDTO jobDetailDTO = GenericConverter.convert(jobEntity, JobDetailDTO.class);

            jobDetailDTO.setTime_create(jobEntity.getCreate_at());
            jobDetailDTO.setTime_expire(jobEntity.getExpire_at());
            jobDetailDTO.getTags().add(jobEntity.getType_work().toString());
            jobDetailDTO.getTags().add(jobEntity.getLocation());
            jobDetailDTO.getTags().add(jobEntity.getExperience());


            jobDetailDTO.setFacility(Arrays.stream(jobEntity.getFacilities().split(", ")).toList());
            return jobDetailDTO;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }

    }


}
