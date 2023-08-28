package backend.findjob.dto.respone;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
//@AllArgsConstructor
public class ResponeObject {
    private String status;
    private String message;
    private Object data;
    private Object error = null;

    public ResponeObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponeObject(String status, String message, Object data, Object error) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.error = error;
    }
}
