package paziylet_uz.controller;

import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import paziylet_uz.payload.dao.MyResponse;
import paziylet_uz.payload.dto.PostDto;
import paziylet_uz.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/post")
public record PostController(
        PostService service,
        Gson gson
) {
    @GetMapping(value = "all")
    public HttpEntity<?> getAllPosts(
            @RequestParam(name = "page") Integer page
    ) {
        return service.getAllPosts(page).handleResponse();
    }

    @GetMapping(value = "search")
    public HttpEntity<?> getAllPosts(
            @RequestParam(name = "tag") String tag
    ) {
        return service.searchPostsWithTag(tag).handleResponse();
    }

    @PostMapping(value = "create")
    public HttpEntity<?> createPost(@Valid @RequestBody PostDto dto) {
        return service.create(dto).handleResponse();
    }

    @PostMapping(value = "create-part")
    public HttpEntity<?> createPostWithImages(
            String body,
            List<MultipartFile> files
    ) {
        PostDto dto;
        try {
            dto = gson.fromJson(body, PostDto.class);
        } catch (Exception e) {
            return MyResponse._ILLEGAL_TYPES.setMessage("Error while parsing json").handleResponse();
        }
        MyResponse create = service.create(dto, files);
        return create.handleResponse();
    }

    @PutMapping(value = "update/{id}")
    public HttpEntity<?> updatePostWithId(
            @PathVariable Long id,
            String body,
            List<MultipartFile> files
    ) {
        PostDto dto;
        try {
            dto = gson.fromJson(body, PostDto.class);
        } catch (Exception e) {
            return MyResponse._ILLEGAL_TYPES.setMessage("Error while parsing json").handleResponse();
        }
        return service.update(id, dto, files).handleResponse();
    }
    @DeleteMapping(value = "remove/{id}")
public HttpEntity<?> removePostWithId(@PathVariable Long id) {
        return service.removePostWithId(id).handleResponse();
    }

    @GetMapping(value = "{id}")
    public HttpEntity<?> getPostWithId(@PathVariable Long id) {
        return service.getPostWithId(id).handleResponse();
    }
}
