package backend.findjob.services.impls;

import backend.findjob.dto.respone.Company.CompanyDTO;
import backend.findjob.entity.CompanyEntity;
import backend.findjob.helper.GenericConverter;
import backend.findjob.services.ICompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements ICompanyService {


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
}
