package pokerface.pokerface.config.error.errorcode;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
    REQUEST_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Request is not allowed"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비빌번호가 올바르지 않습니다."),
    ALREADY_FULL(HttpStatus.METHOD_NOT_ALLOWED, "이미 꽉 찬 방입니다."),
    INVALID_AUTHKEY(HttpStatus.UNAUTHORIZED, "이메일 인증 키가 일치하지 않습니다."),
    EMAIL_NOT_AUTHENTICATED(HttpStatus.UNAUTHORIZED, "이메일 인증이 필요합니다."),
    MATCHING_FAILED(HttpStatus.METHOD_NOT_ALLOWED, "조건에 맞는 방이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}