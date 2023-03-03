package bt.gov.voice.common;

import bt.gov.voice.dao.PublicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 * Created by USER on 6/21/2021.
 */
@Service
public class CommonService {
    @Autowired
    PublicDao publicDao;

    public String getFileEXT(MultipartFile attachment){
        String originalFN = attachment.getOriginalFilename();
        return originalFN.substring(originalFN.lastIndexOf("."));
    }

    /**
     * upload document method
     * @param attachment
     * @param loc
     * @param fileName
     * @return path for upload files
     * @throws Exception
     */
    @Transactional
    public String uploadDocument(MultipartFile attachment,String loc, String fileName) throws Exception{
        String rootPath = null;
        if (attachment != null && !attachment.isEmpty()) {
            //get file upload location from properties file
            ClassPathResource resource = new ClassPathResource("/fileupload/fileupload.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            rootPath= props.getProperty("fileupload.fileUploadPath");
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
            String urlAppender = "/" + calendar.get(Calendar.YEAR) + "/" + dateFormat.format(calendar.getTime()) + "/" + calendar.get(Calendar.DATE) + "/";
            rootPath = rootPath + loc + urlAppender + fileName;

            byte[] bytes = attachment.getBytes();
            Path path = Paths.get(rootPath);

            Path parentDir = path.getParent();
            if (!Files.exists(parentDir))
                Files.createDirectories(parentDir);
            Files.write(path, bytes);
        }
        return rootPath;
    }
}
