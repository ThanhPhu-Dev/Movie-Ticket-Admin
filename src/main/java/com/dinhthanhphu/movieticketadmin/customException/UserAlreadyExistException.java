package com.dinhthanhphu.movieticketadmin.customException;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {
    public UserAlreadyExistException(final String msg) {
        super(msg);
    }
}
