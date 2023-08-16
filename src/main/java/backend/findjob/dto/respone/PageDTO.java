package backend.findjob.dto.respone;

public class PageDTO {
    private int page;
    private int totalPage;

    public PageDTO(int page, int totalPage) {
        this.page = page;
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
