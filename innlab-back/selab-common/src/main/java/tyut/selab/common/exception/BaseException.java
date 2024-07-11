package tyut.selab.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private Integer responseCode;

    public BaseException(Integer responseCode, String message) {
        super(message);

        setResponseCode(responseCode);
    }

}
