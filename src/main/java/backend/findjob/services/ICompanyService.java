package backend.findjob.services;

import backend.findjob.dto.request.CreateCompanyRequest;
import backend.findjob.dto.respone.Company.CompanyDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.CompanyEntity;
import org.springframework.http.ResponseEntity;

public interface ICompanyService {
    public CompanyDTO convertCompanyEntityToDTO(CompanyEntity companyEntity);

    ResponseEntity<ResponeObject> addCompany(CreateCompanyRequest createCompanyRequest);
}
