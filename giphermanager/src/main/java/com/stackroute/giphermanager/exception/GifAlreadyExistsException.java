package com.stackroute.giphermanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="Gif already exists")
public class GifAlreadyExistsException extends Exception {

}
