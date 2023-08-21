package backend.findjob.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponeObject {
    private String status;
    private String message;
    private Object data;
}
