package com.ebi.TechnicalAssignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Mouse Gene Symbol Synonym Not Found.")
public class MouseGeneSymbolSynonymNotFoundException extends Exception {

    private static final long serialVersionUID = -8444267182934630560L;

    public MouseGeneSymbolSynonymNotFoundException(String message) {
        super(message);
    }

}
