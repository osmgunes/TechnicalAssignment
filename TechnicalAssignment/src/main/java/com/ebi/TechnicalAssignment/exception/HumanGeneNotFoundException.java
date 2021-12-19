package com.ebi.TechnicalAssignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Human Gene Matched With Given Symbol")
public class HumanGeneNotFoundException extends Exception {

    private static final long serialVersionUID = 7722352942303668514L;

    public HumanGeneNotFoundException(String message) {
        super(message);
    }

}
