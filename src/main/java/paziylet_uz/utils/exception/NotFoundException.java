package paziylet_uz.utils.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String file_not_found) {
        super(file_not_found);
    }
}
