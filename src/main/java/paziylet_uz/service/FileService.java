package paziylet_uz.service;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public interface FileService {
    void downloadFile(String fileName, HttpServletResponse response);

    void deleteFileWithFileNames(Set<String> fileName);
}
