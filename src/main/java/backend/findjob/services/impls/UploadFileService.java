package backend.findjob.services.impls;

import backend.findjob.helper.Helper;
import backend.findjob.services.IUploadFileService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class UploadFileService implements IUploadFileService {
    @Autowired
    private Cloudinary cloudinary;
    @Override
    public String uploadFile(MultipartFile file) {
        try
        {
            String fileName = Helper.removeExtension(StringUtils.cleanPath(file.getOriginalFilename()));

            if(file.getContentType().contains("image"))
            {
                return cloudinary.uploader()
                        .upload(file.getBytes(), ObjectUtils.asMap(
                                "public_id", UUID.randomUUID().toString(),
                                "folder", "FindJob-upload/Image"
                        )).get("url").toString();
            }
            else if(file.getContentType().contains("pdf"))
            {
                return cloudinary.uploader()
                        .upload(file.getBytes(), ObjectUtils.asMap(
                                "public_id", fileName+"-"+UUID.randomUUID().toString(),
                                "folder", "FindJob-upload/Resume"
                        )).get("url").toString();
            }

            return "";
        }catch (Exception ex)
        {
            throw new RuntimeException(ex);

        }
    }
}
