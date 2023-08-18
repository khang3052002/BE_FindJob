package backend.findjob.api.ProvinceAPI;

import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.ProvinceEntity;
import backend.findjob.repository.ProvinceRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/province")
@Tag(name = "Province")
public class ProvinceAPI {
    @Autowired
    private ProvinceRepository provinceRepository;
    @GetMapping()
    public ResponseEntity<ResponeObject> getAllProvince()
    {
        List<ProvinceEntity> listProvce = provinceRepository.findAll();
        if(listProvce.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponeObject("Not found","",""));

        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponeObject("Ok","",listProvce));
    }
}
