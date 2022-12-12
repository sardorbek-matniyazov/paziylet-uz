package paziylet_uz.service;

import org.springframework.web.multipart.MultipartFile;
import paziylet_uz.payload.dao.MyResponse;
import paziylet_uz.payload.dto.PostDto;

import java.util.List;

public interface PostService {
    MyResponse getAllPosts(Integer page);

    MyResponse create(PostDto dto, List<MultipartFile> files);

    MyResponse create(PostDto dto);

    MyResponse searchPostsWithTag(String tag);
}
