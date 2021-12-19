package com.ebi.TechnicalAssignment.controller;

import com.ebi.TechnicalAssignment.dto.HumanMouseGeneRelationResponse;
import com.ebi.TechnicalAssignment.dto.MultipleHumanGenesResponse;
import com.ebi.TechnicalAssignment.service.OrthologService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relation")
public class OrthologController {

    @Autowired
    private OrthologService orthologService;


    @GetMapping("/symbol/{field}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    public List<HumanMouseGeneRelationResponse> retrieveHumanMouseGeneRelationBySymbol(@PathVariable String field) throws Exception {
        return orthologService.retrieveHumanMouseGeneRelationBySymbol(field);
    }

    @GetMapping("/identifier/{field}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    public List<HumanMouseGeneRelationResponse> retrieveHumanMouseGeneRelationByIdentifier(@PathVariable String field) throws Exception {
        return orthologService.retrieveHumanMouseGeneRelationByIdentifier(field);
    }
    @GetMapping("/MouseGeneSymbol/{field}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    public List<MultipleHumanGenesResponse> retrieveHumanGenesByMouseGeneSymbol(@PathVariable String field) throws Exception {
        return orthologService.retrieveHumanGenesByMouseGeneSymbol(field);
    }
}
