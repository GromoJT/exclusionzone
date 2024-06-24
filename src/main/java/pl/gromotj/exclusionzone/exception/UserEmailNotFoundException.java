package pl.gromotj.exclusionzone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserEmailNotFoundException extends AuthenticationException {
    public UserEmailNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserEmailNotFoundException(String msg) {
        super(msg);
    }
}
