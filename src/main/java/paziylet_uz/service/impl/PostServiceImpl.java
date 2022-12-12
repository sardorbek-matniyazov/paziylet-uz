package paziylet_uz.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import paziylet_uz.domain.Attachment;
import paziylet_uz.domain.Post;
import paziylet_uz.payload.dao.MyResponse;
import paziylet_uz.payload.dao.PagingResponse;
import paziylet_uz.payload.dto.PostDto;
import paziylet_uz.repository.FileRepository;
import paziylet_uz.repository.PostRepository;
import paziylet_uz.service.PostService;
import paziylet_uz.utils.exception.AlreadyExistsException;
import paziylet_uz.utils.exception.TypesInError;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static paziylet_uz.payload.dao.MyResponse._CREATED;
import static paziylet_uz.payload.dao.MyResponse._DATA;
import static paziylet_uz.utils.constants.PathConstants._UPLOAD_DIR;
import static paziylet_uz.utils.constants.SizeConstants._PAGE_LIMIT;

@Service
public record PostServiceImpl(
        PostRepository repository,
        FileRepository fileRepository
) implements PostService {

    @Override
    public MyResponse getAllPosts(Integer page) {
        final Page<Post> all = repository.findAll(
                PageRequest.of(
                        page - 1,
                        _PAGE_LIMIT,
                        Sort.by(
                                Sort.Direction.DESC,
                                "id"
                        )
                )
        );
        return _DATA
                .putData("data", all.getContent())
                .putData("pageable", new PagingResponse(
                                all.getTotalPages(),
                                all.getTotalElements(),
                                all.getNumber(),
                                all.getSize(),
                                all.nextOrLastPageable().getPageNumber(),
                                all.nextOrLastPageable().getPageSize(),
                                all.isLast()
                        )
                );
    }

    @Override
    public MyResponse create(PostDto dto) {
        if (repository.existsByTitle(dto.getTitle()))
            throw new AlreadyExistsException("Post with name " + dto.getTitle() + " already exists");
        return _CREATED
                .setMessage("Post created")
                .putData("data", repository.save(dto.toEntity().setQuerySearch()));
    }

    @Override
    public MyResponse searchPostsWithTag(String tag) {
        return _DATA
                .putData("data", repository.findAllBySearchQueryContaining(tag));
    }

    @Override
    public MyResponse create(@Valid PostDto dto, List<MultipartFile> files) {
        if (dto.getTitle() == null || dto.getDescription() == null)
            throw new TypesInError("Title or description is null");

        if (repository.existsByTitle(dto.getTitle()))
            throw new AlreadyExistsException("Post with name " + dto.getTitle() + " already exists");

        final Set<String> strings = saveImages(files);
        return _CREATED
                .setMessage("Post created")
                .putData("data", repository.save(dto.toEntity().setQuerySearch().setImages(strings)));
    }

    private Set<String> saveImages(List<MultipartFile> files) {
        return files.stream().filter(file -> file != null && !file.isEmpty()).map(
                image -> {
                    StringBuilder builder = new StringBuilder(UUID.randomUUID().toString());
                    String[] split = Objects.requireNonNull(image.getOriginalFilename()).split("\\.");
                    builder.append(".").append(split[split.length - 1]);

                    Path path = Paths.get(_UPLOAD_DIR + builder);
                    try {
                        Files.copy(image.getInputStream(), path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    fileRepository.save(
                            new Attachment(
                                    image.getOriginalFilename(),
                                    builder.toString(),
                                    image.getContentType(),
                                    image.getSize()
                            )
                    );
                    return builder.toString();
                }
        ).collect(Collectors.toSet());
    }
}
