package backend.findjob.services.impls;

import backend.findjob.dto.request.CreateCompanyRequest;
import backend.findjob.dto.respone.Company.CompanyDTO;
import backend.findjob.dto.respone.ResponeObject;
import backend.findjob.entity.CompanyEntity;
import backend.findjob.helper.GenericConverter;
import backend.findjob.repository.CompanyRepository;
import backend.findjob.services.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    public CompanyDTO convertCompanyEntityToDTO(CompanyEntity companyEntity) {
        try
        {
            CompanyDTO companyDTO = GenericConverter.convert(companyEntity, CompanyDTO.class);
            return companyDTO;
        }catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());

        }

    }

    @Override
    public ResponseEntity<ResponeObject> addCompany(CreateCompanyRequest createCompanyRequest) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName(createCompanyRequest.getName());
        companyEntity.setImage(createCompanyRequest.getImage());
        companyEntity.setIntroduce(createCompanyRequest.getIntroduce());
        companyEntity.setLocation(createCompanyRequest.getLocation());
        companyEntity.setSpecialization(createCompanyRequest.getSpecialization());
        companyEntity.setWebsite(createCompanyRequest.getWebsite());
        companyEntity.setType_company(createCompanyRequest.getType_company());
        companyEntity.setEmployee_number(createCompanyRequest.getEmployee_number());

        companyRepository.save(companyEntity);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("Success","Create company success",""));


    }
}
