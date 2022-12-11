package paziylet_uz.service;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    void downloadFile(String fileName, HttpServletResponse response);
}
