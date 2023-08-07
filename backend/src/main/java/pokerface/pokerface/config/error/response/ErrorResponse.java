package pokerface.pokerface.config.error.response;

import java.time.LocalDateTime;
import lombok.Getter;
import pokerface.pokerface.config.error.errorcode.ErrorCode;

/**
 * 응답으로 내려줄 예외 형식을 지정
 */
@Getter
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int statusCode;
    private final String error;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.statusCode = errorCode.getHttpStatus().value();
        this.error = errorCode.getHttpStatus().name();
        this.message = errorCode.getMessage();
    }
}
