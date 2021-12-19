package com.ebi.TechnicalAssignment.service;


import com.ebi.TechnicalAssignment.Constant.Constant;
import com.ebi.TechnicalAssignment.config.TestConfig;
import com.ebi.TechnicalAssignment.dto.MouseGeneSymbolSynonymResponse;
import com.ebi.TechnicalAssignment.exception.MouseGeneSymbolSynonymNotFoundException;
import com.ebi.TechnicalAssignment.model.mouse.MouseGene;
import com.ebi.TechnicalAssignment.model.mouse.MouseGeneSynonym;
import com.ebi.TechnicalAssignment.model.mouse.MouseGeneSynonymRelation;
import com.ebi.TechnicalAssignment.repository.MouseGeneSynonymRelationRepository;
import com.ebi.TechnicalAssignment.tesdata.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class MouseGeneSynonymRelationServiceTest {


    @InjectMocks
    MouseGeneSynonymRelationService mouseGeneSynonymRelationService;

    @Mock
    MouseGeneSynonymRelationRepository mouseGeneSynonymRelationRepository;

    @Test
    @DisplayName("Should throw Not Found Exception If no data found By given symbol")
    void retrieveMouseBySymbol_ThrowException() {
        //WHEN
        Exception exception = Assertions.assertThrows(MouseGeneSymbolSynonymNotFoundException.class, () -> {
            mouseGeneSynonymRelationService.retrieveMouseGeneSymbolSynonymBySymbol("Dummy");
        });

        String actualMessage = exception.getMessage();

        //THEN
        Assertions.assertTrue(actualMessage.contains(Constant.MOUSE_GENE_SYMBOL_SYNONYM_NOT_FOUND));

    }

    @Test
    @DisplayName("Should return Correct Synonym and Symbol If data found By given symbol")
    void retrieveMouseBySymbol_ReturnRelation() throws MouseGeneSymbolSynonymNotFoundException {
        Mockito.when(mouseGeneSynonymRelationRepository.findByMouseGeneSymbol(Mockito.anyString())).thenReturn(TestData.dummyMouseGeneSynonymRelationList());

        List<MouseGeneSymbolSynonymResponse> mouseGeneSymbolSynonymResponses = mouseGeneSynonymRelationService.retrieveMouseGeneSymbolSynonymBySymbol("Fgf8");

        //THEN
        Assertions.assertEquals(mouseGeneSymbolSynonymResponses.get(0).getSynonym(),"Aigf");
        Assertions.assertEquals(mouseGeneSymbolSynonymResponses.get(0).getSymbol(),"Fgf8");
    }

    @Test
    @DisplayName("Should throw Not Found Exception If no data found By given synonym")
    void retrieveMouseBySynonym_ThrowException() {

        //WHEN
        Exception exception = Assertions.assertThrows(MouseGeneSymbolSynonymNotFoundException.class, () -> {
            mouseGeneSynonymRelationService.retrieveMouseGeneSymbolSynonymBySynonym("Dummy");
        });
        String actualMessage = exception.getMessage();

        //THEN
        Assertions.assertTrue(actualMessage.contains(Constant.MOUSE_GENE_SYMBOL_SYNONYM_NOT_FOUND));

    }

    @Test
    @DisplayName("Should return Correct Synonym and Symbol If data found By given Synonym")
    void retrieveMouseBySynonym_ReturnRelation() throws MouseGeneSymbolSynonymNotFoundException {
        Mockito.when(mouseGeneSynonymRelationRepository.findByMouseGeneSynonymSynonym(Mockito.anyString())).thenReturn(TestData.dummyMouseGeneSynonymRelationList());

        List<MouseGeneSymbolSynonymResponse> mouseGeneSymbolSynonymResponses = mouseGeneSynonymRelationService.retrieveMouseGeneSymbolSynonymBySynonym("Fgf8");

        //THEN
        Assertions.assertEquals(mouseGeneSymbolSynonymResponses.get(0).getSynonym(),"Aigf");
        Assertions.assertEquals(mouseGeneSymbolSynonymResponses.get(0).getSymbol(),"Fgf8");
    }


}
