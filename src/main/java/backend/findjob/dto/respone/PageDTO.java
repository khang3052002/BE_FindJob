package backend.findjob.dto.respone;

public class PageDTO {
    private int currPage;
    private int totalPage;

    public PageDTO(int page, int totalPage) {
        this.currPage = page;
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
