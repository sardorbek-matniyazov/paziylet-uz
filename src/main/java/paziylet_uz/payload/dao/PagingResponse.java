package paziylet_uz.payload.dao;

public record PagingResponse (
        Integer totalPages,
        Long totalItems,

        Integer currentPageNumber,
        Integer currentPageSize,

        Integer nextPageNumber,
        Integer nextPageSize,

        Boolean isLast
) {
}
