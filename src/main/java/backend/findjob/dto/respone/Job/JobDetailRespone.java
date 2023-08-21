package backend.findjob.dto.respone.Job;

import backend.findjob.dto.respone.Company.CompanyDTO;
import backend.findjob.dto.respone.PageDTO;
import org.springframework.data.domain.Page;

public class JobDetailRespone {
    private PageDTO page;
    private JobDetailDTO job;
    private CompanyDTO company;

    public JobDetailDTO getJob() {
        return job;
    }

    public JobDetailRespone(JobDetailDTO job, CompanyDTO company, PageDTO page) {
        this.job = job;
        this.company = company;
        this.page = page;
    }

    public JobDetailRespone(JobDetailDTO job, CompanyDTO company) {
        this.job = job;
        this.company = company;
    }

    public PageDTO getPage() {
        return page;
    }

    public void setPage(PageDTO page) {
        this.page = page;
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
