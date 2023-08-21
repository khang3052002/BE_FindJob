package backend.findjob.dto;

public class ListDTO {
    private Long totalItem;
    private Object object;

    public ListDTO(Long totalItem, Object object) {
        this.totalItem = totalItem;
        this.object = object;
    }

    public Long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Long totalItem) {
        this.totalItem = totalItem;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
