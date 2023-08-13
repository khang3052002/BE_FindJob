package backend.findjob.dto.respone.Job;

import backend.findjob.dto.respone.Company.CompanyDTO;

public class JobDetailRespone {
    private JobDetailDTO job;
    private CompanyDTO company;

    public JobDetailDTO getJob() {
        return job;
    }

    public JobDetailRespone(JobDetailDTO job, CompanyDTO company) {
        this.job = job;
        this.company = company;
    }

    public void setJob(JobDetailDTO job) {
        this.job = job;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }
}
