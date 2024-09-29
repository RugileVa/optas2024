package optas.lt.battleship.utilities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
public class BattleshipResponse {

    private String message;
    private HttpStatus httpStatus;
    private Integer statusCode;
    private Object data;

    public BattleshipResponse(String message, HttpStatus httpStatus, Map<String, Object> data) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.statusCode = (httpStatus != null) ? httpStatus.value() : null;
        this.data = data;
    }
}

