package backend.findjob.dto.respone.Job;

import backend.findjob.dto.respone.PageDTO;

import java.util.ArrayList;
import java.util.List;

public class JobDTORespone {
    private PageDTO page;
    private List<JobDTO> listJob = new ArrayList<>();

    public JobDTORespone(PageDTO page, List<JobDTO> listJob) {
        this.page = page;
        this.listJob = listJob;
    }

    public PageDTO getPage() {
        return page;
    }

    public void setPage(PageDTO page) {
        this.page = page;
    }

    public List<JobDTO> getListJob() {
        return listJob;
    }

    public void setListJob(List<JobDTO> listJob) {
        this.listJob = listJob;
    }
}
