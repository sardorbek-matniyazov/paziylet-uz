package paziylet_uz.payload.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyResponse {

    public static final MyResponse _DATA = new MyResponse();
    public static final MyResponse _CREATED = new MyResponse(HttpStatus.CREATED);
    public static final MyResponse _ALREADY_EXISTS = new MyResponse(HttpStatus.BAD_REQUEST, false);
    public static final MyResponse _BAD_REQUEST = new MyResponse(HttpStatus.BAD_REQUEST, false);
    public static final MyResponse _NOT_FOUND = new MyResponse(HttpStatus.BAD_REQUEST, false);
    public static final MyResponse _ILLEGAL_TYPES = new MyResponse(HttpStatus.BAD_REQUEST, false);

    private String message = "OK";
    private Integer status = 200;
    private Map<String, Object> body = new HashMap<>();
    private Boolean active = true;

    public MyResponse(HttpStatus created) {
        this.status = created.value();
    }

    public MyResponse(HttpStatus badRequest, boolean b) {
        this.status = badRequest.value();
        this.active = b;
    }

    public MyResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public MyResponse putData(String key, Object value) {
        this.body.put(key, value);
        return this;
    }

    public ResponseEntity<Object> handleResponse() {
        return ResponseEntity.status(this.status).body(this);
    }
}
