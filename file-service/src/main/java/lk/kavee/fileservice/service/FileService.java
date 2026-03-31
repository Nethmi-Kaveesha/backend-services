package lk.kavee.fileservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    public String uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        try {
            String tmpDir = System.getProperty("java.io.tmpdir");
            File convertFile = new File(tmpDir, originalFilename);
            file.transferTo(convertFile);
            return "File path : " + convertFile.getAbsolutePath();
        } catch (IOException e) {
            return "Failed to upload file!";
        }
    }
}
