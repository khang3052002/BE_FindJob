package backend.findjob.services;

import backend.findjob.dto.respone.Company.CompanyDTO;
import backend.findjob.entity.CompanyEntity;

public interface ICompanyService {
    public CompanyDTO convertCompanyEntityToDTO(CompanyEntity companyEntity);

    }
