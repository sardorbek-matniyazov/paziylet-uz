package paziylet_uz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import paziylet_uz.service.FileService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "file")
public record FileController(FileService service) {

    @GetMapping(value = "download/{fileName}")
    public void downloadFile(@PathVariable(value = "fileName") String fileName, HttpServletResponse response) {
        service.downloadFile(fileName, response);
    }
}
