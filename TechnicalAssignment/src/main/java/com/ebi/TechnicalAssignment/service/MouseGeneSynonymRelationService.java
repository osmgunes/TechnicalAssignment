package com.ebi.TechnicalAssignment.service;


import com.ebi.TechnicalAssignment.dto.MouseGeneSymbolSynonymResponse;
import com.ebi.TechnicalAssignment.exception.MouseGeneSymbolSynonymNotFoundException;
import com.ebi.TechnicalAssignment.model.mouse.MouseGeneSynonymRelation;
import com.ebi.TechnicalAssignment.repository.MouseGeneSynonymRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ebi.TechnicalAssignment.Constant.Constant.MOUSE_GENE_SYMBOL_SYNONYM_NOT_FOUND;

@Service
public class MouseGeneSynonymRelationService {


    private MouseGeneSynonymRelationRepository mouseGeneSynonymRelationRepository;


    @Autowired
    public void MouseGeneSynonymRelationRepository(MouseGeneSynonymRelationRepository mouseGeneSynonymRelationRepository) {
        this.mouseGeneSynonymRelationRepository = mouseGeneSynonymRelationRepository;
    }

    public List<MouseGeneSymbolSynonymResponse> retrieveMouseGeneSymbolSynonymBySymbol(String symbol) throws MouseGeneSymbolSynonymNotFoundException {
        List<MouseGeneSymbolSynonymResponse> mouseGeneSymbolSynonymResponses = mouseGeneSynonymRelationRepository.findByMouseGeneSymbol(symbol)
                .stream()
                .map(getGeneSymbolAndSynonym())
                .collect(Collectors.toList());

        if (mouseGeneSymbolSynonymResponses.size() == 0) {
            throw new MouseGeneSymbolSynonymNotFoundException(String.format(MOUSE_GENE_SYMBOL_SYNONYM_NOT_FOUND));
        }

        return mouseGeneSymbolSynonymResponses;
    }

    public List<MouseGeneSymbolSynonymResponse> retrieveMouseGeneSymbolSynonymBySynonym(String synonym) throws MouseGeneSymbolSynonymNotFoundException {

        List<MouseGeneSymbolSynonymResponse> mouseGeneSymbolSynonymResponses = mouseGeneSynonymRelationRepository.findByMouseGeneSynonymSynonym(synonym)
                .stream()
                .map(getGeneSymbolAndSynonym())
                .collect(Collectors.toList());

        if (mouseGeneSymbolSynonymResponses.size() == 0) {
            throw new MouseGeneSymbolSynonymNotFoundException(String.format(MOUSE_GENE_SYMBOL_SYNONYM_NOT_FOUND));
        }

        return mouseGeneSymbolSynonymResponses;
    }


    private Function<MouseGeneSynonymRelation, MouseGeneSymbolSynonymResponse> getGeneSymbolAndSynonym() {
        return mouseGeneSynonym -> convertToMouseGeneSymbolSynonym()
                .apply(mouseGeneSynonym.getMouseGene().getSymbol(), mouseGeneSynonym.getMouseGeneSynonym().getSynonym());
    }

    private BiFunction<String, String, MouseGeneSymbolSynonymResponse> convertToMouseGeneSymbolSynonym() {
        return (mgiGeneAccId, synonym) -> MouseGeneSymbolSynonymResponse
                .builder()
                .symbol(mgiGeneAccId)
                .synonym(synonym)
                .build();
    }
}
