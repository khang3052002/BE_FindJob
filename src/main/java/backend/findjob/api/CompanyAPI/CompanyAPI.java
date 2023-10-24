package backend.findjob.api.CompanyAPI;

import backend.findjob.dto.request.CreateCompanyRequest;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.services.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyAPI {
    @Autowired
    private ICompanyService companyService;
    @PostMapping("/add")
    public ResponseEntity<ResponeObject> addCompany(@RequestBody CreateCompanyRequest createCompanyRequest)
    {
        return companyService.addCompany(createCompanyRequest);
    }
}
