package com.ebi.TechnicalAssignment.controller;


import com.ebi.TechnicalAssignment.dto.MouseGeneSymbolSynonymResponse;
import com.ebi.TechnicalAssignment.service.MouseGeneSynonymRelationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mouse")
public class MouseGeneSynonymRelationController {

    @Autowired
    private MouseGeneSynonymRelationService mouseGeneSynonymRelationService;

    @GetMapping("/symbol/{value}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    public List<MouseGeneSymbolSynonymResponse> retrieveMouseBySymbol(@PathVariable String value) throws Exception {
        return mouseGeneSynonymRelationService.retrieveMouseGeneSymbolSynonymBySymbol(value);
    }

    @GetMapping("/synonym/{value}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    public List<MouseGeneSymbolSynonymResponse> retrieveMouseBySynonym(@PathVariable String value) throws Exception {
        return mouseGeneSynonymRelationService.retrieveMouseGeneSymbolSynonymBySynonym(value);
    }
}
