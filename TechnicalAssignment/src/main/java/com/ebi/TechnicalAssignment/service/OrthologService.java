package com.ebi.TechnicalAssignment.service;


import com.ebi.TechnicalAssignment.dto.HumanMouseGeneRelationResponse;
import com.ebi.TechnicalAssignment.dto.MultipleHumanGenesResponse;
import com.ebi.TechnicalAssignment.exception.HumanGeneNotFoundException;
import com.ebi.TechnicalAssignment.exception.MouseGeneSymbolSynonymNotFoundException;
import com.ebi.TechnicalAssignment.exception.NoRelationFoundException;
import com.ebi.TechnicalAssignment.model.ortholog.Ortholog;
import com.ebi.TechnicalAssignment.repository.OrthologRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ebi.TechnicalAssignment.Constant.Constant.NO_HUMAN_GENE_MATCHED_WITH_GIVEN_SYMBOL;
import static com.ebi.TechnicalAssignment.Constant.Constant.NO_RELATION_FOUND_FOR_GIVEN_INPUT;

@Service
public class OrthologService {

    private OrthologRepository orthologRepository;

    @Autowired
    public void MouseGeneSynonymRepository(OrthologRepository orthologRepository) {
        this.orthologRepository = orthologRepository;
    }

    public List<HumanMouseGeneRelationResponse> retrieveHumanMouseGeneRelationBySymbol(String symbol) throws NoRelationFoundException {
        List<HumanMouseGeneRelationResponse> humanMouseGeneRelationResponses= orthologRepository.findByMouseGeneSymbol(symbol)
                .stream()
                .map(convertToHumanMouseGeneRelationResponse())
                .collect(Collectors.toList());

        if (humanMouseGeneRelationResponses.size() == 0) {
            throw new NoRelationFoundException(String.format(NO_RELATION_FOUND_FOR_GIVEN_INPUT));
        }

        return  humanMouseGeneRelationResponses;
    }

    public List<HumanMouseGeneRelationResponse> retrieveHumanMouseGeneRelationByIdentifier(String Identifier) throws  NoRelationFoundException {
        List<HumanMouseGeneRelationResponse> humanMouseGeneRelationResponses= orthologRepository.findByMouseGeneMgiGeneAccId(Identifier)
                .stream()
                .map(convertToHumanMouseGeneRelationResponse())
                .collect(Collectors.toList());
        if (humanMouseGeneRelationResponses.size() == 0) {
            throw new NoRelationFoundException(String.format(NO_RELATION_FOUND_FOR_GIVEN_INPUT));
        }
        return  humanMouseGeneRelationResponses;
    }

    public List<MultipleHumanGenesResponse> retrieveHumanGenesByMouseGeneSymbol(String MouseGeneSymbol) throws HumanGeneNotFoundException {
        List<MultipleHumanGenesResponse> multipleHumanGenesResponses= orthologRepository.findByMouseGeneSymbol(MouseGeneSymbol)
                .stream()
                .map(convertToHMultipleHumanGenesResponse())
                .collect(Collectors.toList());
        if (multipleHumanGenesResponses.size() == 0) {
            throw new HumanGeneNotFoundException(String.format(NO_HUMAN_GENE_MATCHED_WITH_GIVEN_SYMBOL));
        }
        return  multipleHumanGenesResponses;
    }

    private Function<Ortholog, HumanMouseGeneRelationResponse> convertToHumanMouseGeneRelationResponse() {
        return (orthologs) -> HumanMouseGeneRelationResponse
                .builder()
                .mouseGene(orthologs.getMouseGene())
                .humanGene(orthologs.getHumanGene())
                .build();
    }

    private Function<Ortholog, MultipleHumanGenesResponse> convertToHMultipleHumanGenesResponse() {
        return (orthologs) -> MultipleHumanGenesResponse
                .builder()
                .humanGene(orthologs.getHumanGene())
                .supportCount(orthologs.getSupportCount())
                .build();
    }
}
