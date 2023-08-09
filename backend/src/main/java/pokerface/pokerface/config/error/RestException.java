package pokerface.pokerface.config.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pokerface.pokerface.config.error.errorcode.ErrorCode;

@Getter
@RequiredArgsConstructor
public class RestException extends RuntimeException {
    private final ErrorCode errorCode;
}
