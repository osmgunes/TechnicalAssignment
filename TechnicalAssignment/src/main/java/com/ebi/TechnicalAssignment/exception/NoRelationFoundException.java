package com.ebi.TechnicalAssignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Relation Found For Given Input.")
public class NoRelationFoundException extends Exception {
    static final long serialVersionUID = -3387516993334229948L;


    public NoRelationFoundException(String message) {
        super(message);
    }

}
