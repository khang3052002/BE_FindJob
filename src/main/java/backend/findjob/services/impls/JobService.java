package backend.findjob.services.impls;

import backend.findjob.dto.AuthenticateDTO;
import backend.findjob.dto.ListDTO;
import backend.findjob.dto.respone.Company.CompanyDTO;
import backend.findjob.dto.respone.Job.JobDTO;
import backend.findjob.dto.respone.Job.JobDTORespone;
import backend.findjob.dto.respone.Job.JobDetailDTO;
import backend.findjob.dto.respone.Job.JobDetailRespone;
import backend.findjob.dto.respone.PageDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.CVEntity;
import backend.findjob.entity.Enum.TypeWork;
import backend.findjob.entity.Enum.TypeWorkPlace;
import backend.findjob.entity.JobEntity;
import backend.findjob.entity.UserEntity;
import backend.findjob.helper.GenericConverter;
import backend.findjob.helper.Helper;
import backend.findjob.repository.JobRepository;
import backend.findjob.repository.UserRepository;
import backend.findjob.services.ICompanyService;
import backend.findjob.services.IJobService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class JobService implements IJobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ICompanyService companyService;

    @Override
    public ResponseEntity<ResponeObject> getAll(Pageable page) {

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

        List<JobEntity> listJob = jobRepository.findAll(page).getContent();


        List<JobDTO> listJobDTO = new ArrayList<>();
        try {
            for (JobEntity jobEntity : listJob) {
                JobDTO job = convertJobEntityToJobDTO_card(jobEntity,id_user);
                listJobDTO.add(job);

            }
        } catch (Exception e) {
//            throw new RuntimeException(e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponeObject("Fail", e.getMessage(), ""));
        }

        PageDTO pageDTO = new PageDTO(page.getPageNumber() + 1,(int) Math.ceil((double) totalItem()/ page.getPageSize()));
        JobDTORespone respone = new JobDTORespone(pageDTO,listJobDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("Success", "Get all job list successfull", respone));
    }
    @Override
    public ResponseEntity<ResponeObject> getJobById(Long idJob) {
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

        JobEntity jobEntity = jobRepository.findById(idJob).orElse(null);

        if(jobEntity == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponeObject("Not found","Job not exists", ""));
        }



        JobDetailDTO jobDetailDTO = convertJobEntityToDTO(jobEntity, id_user);
        CompanyDTO companyDTO = companyService.convertCompanyEntityToDTO(jobEntity.getCompany());

        JobDetailRespone jobDetailRespone = new JobDetailRespone(jobDetailDTO,companyDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("Success", "Get job successfull", jobDetailRespone));


    }

    @Override
    public ResponseEntity<ResponeObject> saveJob(Long idJob) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class)
        {
            authenticateDTO = (AuthenticateDTO) authentication.getPrincipal();

            JobEntity job = jobRepository.findById(idJob).orElse(null);
            UserEntity user = userRepository.findById(authenticateDTO.getId()).orElse(null);
            if(job == null)
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Job not exists",""));

            }
            if(user == null)
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","User not exists",""));
            }

            user.getListJobSave().add(job);
            user = userRepository.save(user);


            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Save job successfull",""));

        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to unsave job",""));
        }
    }

    @Override
    public ResponseEntity<ResponeObject> unsaveJob(Long idJob) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticateDTO authenticateDTO  = null;
        // Ai co xac thuc thi moi dc up load
        if(authentication.getPrincipal().getClass() == AuthenticateDTO.class)
        {
            authenticateDTO = (AuthenticateDTO) authentication.getPrincipal();

            JobEntity job = jobRepository.findById(idJob).orElse(null);
            UserEntity user = userRepository.findById(authenticateDTO.getId()).orElse(null);
            if(job == null)
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","Job not exists",""));

            }
            if(user == null)
            {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponeObject("Not found","User not exists",""));
            }

            boolean ok = user.getListJobSave().remove(job);
            user = userRepository.save(user);


            return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Unsave job successfull",""));

        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("Unauthorized","Only users are allowed to unsave job ",""));
        }
    }

    @Override
    public ResponseEntity<ResponeObject> getSaveListFromUser(Long idUser) {
        UserEntity user = userRepository.findById(idUser).orElse(null);

        if(user == null)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponeObject("Not found","User not exists",""));
        }
        List<JobDTO> listJobDTO = new ArrayList<>();
        List<JobEntity> listJob = user.getListJobSave();
        if(listJob.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponeObject("Empty","List job save is empty",""));
        }
        try {
            for (JobEntity jobEntity : listJob) {
                JobDTO job = GenericConverter.convert(jobEntity, JobDTO.class);
                job.setTime_create(jobEntity.getCreate_at());
                job.setNameCompany(jobEntity.getCompany().getName());
                job.setId_company(jobEntity.getCompany().getId());
                job.getTags().add(jobEntity.getType_work().toString());
                job.getTags().add(jobEntity.getLocation());
                job.getTags().add(jobEntity.getExperience());
                job.setNumDayPost(Helper.convertDayAgoToNow(jobEntity.getCreate_at()));
                job.setNumDayExpire(Helper.convertDayCreateToExpire(jobEntity.getCreate_at(),jobEntity.getExpire_at()).toString());
                job.setIs_saved(true);
                // check is save

                listJobDTO.add(job);

            }


        }catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("Success", "Get job save list successfull", listJobDTO));
    }

    @Override
    public int totalItem() {
        return (int) jobRepository.count();
    }

    @Override
    public ResponseEntity<ResponeObject> getJobByFilter(TypeWorkPlace workplace, TypeWork jobtype, String pos
            , String city, String exp, List<String> specialization, Double salary_min, Double salary_max,Pageable pageable) {
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

        ListDTO listResult = jobRepository.findJobByFilTer(workplace,jobtype,pos,city,exp,specialization, salary_min, salary_max,pageable);
        List<JobEntity> listJob = (List<JobEntity>) listResult.getObject();
        if(listJob.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponeObject("Empty","List job save is empty",""));
        }

        List<JobDTO> listJobDTO = new ArrayList<>();
        try {
            for (JobEntity jobEntity : listJob) {
                JobDTO job = convertJobEntityToJobDTO_card(jobEntity,id_user);
                listJobDTO.add(job);
            }
        } catch (Exception e) {
//            throw new RuntimeException(e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponeObject("Fail", e.getMessage(), ""));
        }

        PageDTO pageDTO = new PageDTO(pageable.getPageNumber() + 1,(int) Math.ceil((double) listResult.getTotalItem()/ pageable.getPageSize()));
        JobDTORespone respone = new JobDTORespone(pageDTO,listJobDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("Success", "Get all job list successfull", respone));

    }

    @Override
    public ResponseEntity<ResponeObject> searchJobByCityAndKeyword(String keyword, String codeCity,Pageable pageable) {

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

        ListDTO listResult =  jobRepository.findJobByCodeCityAndKeyword(codeCity,keyword,pageable);

        List<JobEntity> listJob = (List<JobEntity>)listResult.getObject();
        if(listJob.isEmpty())
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponeObject("Empty","List job save is empty",""));
        }

        List<JobDTO> listJobDTO = new ArrayList<>();
        try {
            for (JobEntity jobEntity : listJob) {
                JobDTO job = convertJobEntityToJobDTO_card(jobEntity,id_user);
                listJobDTO.add(job);
            }
        } catch (Exception e) {
//            throw new RuntimeException(e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponeObject("Fail", e.getMessage(), ""));
        }


        PageDTO pageDTO = new PageDTO(pageable.getPageNumber() + 1,(int) Math.ceil((double) listResult.getTotalItem()/ pageable.getPageSize()));
        JobDTORespone respone = new JobDTORespone(pageDTO,listJobDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("Success", "Get all job list successfull", respone));

    }

    public JobDetailDTO convertJobEntityToDTO(JobEntity jobEntity, Long id_user)
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



            if (id_user == null) {
                jobDetailDTO.setIs_saved(false);
            } else {
                UserEntity user = userRepository.findById(id_user).orElse(null);
                List<UserEntity> listUserSave = jobEntity.getListUser();
                if (listUserSave.isEmpty()) {
                    jobDetailDTO.setIs_saved(false);
                } else {
                    boolean checkSave = listUserSave.contains(user);
                    if(checkSave)
                    {
                        jobDetailDTO.setIs_saved(true);
                    }
                    else{
                        jobDetailDTO.setIs_saved(false);
                    }

//                    job.setIs_saved(false);
//                    for (UserEntity user : listUserSave) {
//                        if (user.getId().equals(id_user)) {
//                            job.setIs_saved(true);
//                            break;
//                        }
//                    }

                }

            }

            return jobDetailDTO;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }

    }
    public JobDTO convertJobEntityToJobDTO_card(JobEntity jobEntity, Long id_user)
    {
        try
        {
            JobDTO job = GenericConverter.convert(jobEntity, JobDTO.class);
            job.setTime_create(jobEntity.getCreate_at());
            job.setNameCompany(jobEntity.getCompany().getName());
            job.setId_company(jobEntity.getCompany().getId());
            job.getTags().add(jobEntity.getType_work().toString());
            job.getTags().add(jobEntity.getProvince().getName());
            job.getTags().add(jobEntity.getExperience());

            job.setNumDayPost(Helper.convertDayAgoToNow(jobEntity.getCreate_at()));
            job.setNumDayExpire(Helper.convertDayCreateToExpire(jobEntity.getCreate_at(),jobEntity.getExpire_at()).toString());
//                job.getTags().add(Helper.convertDay(jobEntity.getCreate_at()));
            // check is save
            if (id_user == null) {
                job.setIs_saved(false);
            } else {
                List<UserEntity> listUserSave = jobEntity.getListUser();
                UserEntity user = userRepository.findById(id_user).orElse(null);
                if (listUserSave.isEmpty()) {
                    job.setIs_saved(false);
                } else {
                    boolean checkSave = listUserSave.contains(user);
                    if(checkSave)
                    {
                        job.setIs_saved(true);
                    }
                    else{
                        job.setIs_saved(false);
                    }

                }

            }
            return job;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());

        }

    }

}
