package paziylet_uz.service.impl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import paziylet_uz.domain.Attachment;
import paziylet_uz.repository.FileRepository;
import paziylet_uz.service.FileService;
import paziylet_uz.utils.exception.NotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

import static paziylet_uz.utils.constants.PathConstants._UPLOAD_DIR;

@Service
public record FileServiceImpl (
        FileRepository repository
) implements FileService {

    @SneakyThrows
    @Override
    public void downloadFile(String fileName, HttpServletResponse response) {
        final Optional<Attachment> byFileName = repository.findByFileName(fileName);
        final Attachment image = byFileName.orElseThrow(
                () -> new NotFoundException("File not found")
        );

        File file = new File(_UPLOAD_DIR + fileName);
        response.setContentType(image.getContentType());
        FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
    }
}
