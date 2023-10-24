package backend.findjob.services;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
    public String uploadFile(MultipartFile file);
}
