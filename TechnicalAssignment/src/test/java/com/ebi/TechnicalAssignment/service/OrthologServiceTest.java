package com.ebi.TechnicalAssignment.service;

import com.ebi.TechnicalAssignment.constant.Constant;
import com.ebi.TechnicalAssignment.config.TestConfig;
import com.ebi.TechnicalAssignment.dto.HumanMouseGeneRelationResponse;
import com.ebi.TechnicalAssignment.dto.MultipleHumanGenesResponse;
import com.ebi.TechnicalAssignment.exception.HumanGeneNotFoundException;
import com.ebi.TechnicalAssignment.exception.NoRelationFoundException;
import com.ebi.TechnicalAssignment.repository.OrthologRepository;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
class OrthologServiceTest {


    @InjectMocks
    OrthologService orthologService;

    @Mock
    OrthologRepository orthologRepository;

    @Test
    @DisplayName("Should throw Exception if There is no Relation with given symbol")
    void retrieveHumanMouseGeneRelationBySymbol_ThrowException() {
        //WHEN
        Exception exception = Assertions.assertThrows(NoRelationFoundException.class, () ->
                orthologService.retrieveHumanMouseGeneRelationBySymbol("Fgf8"));

        String actualMessage = exception.getMessage();

        //THEN
        Assertions.assertTrue(actualMessage.contains(Constant.NO_RELATION_FOUND_FOR_GIVEN_INPUT));
    }

    @Test
    @DisplayName("Should return Correct HumanMouseGeneRelationResponse if Relation found with given symbol")
    void retrieveHumanMouseGeneRelationBySymbol_ReturnRelation() throws NoRelationFoundException {
        //WHEN
        Mockito.when(orthologRepository.findByMouseGeneSymbol(Mockito.anyString())).thenReturn(TestData.dummyOrthologList());

        List<HumanMouseGeneRelationResponse> humanMouseGeneRelationResponses = orthologService.retrieveHumanMouseGeneRelationBySymbol("Fgf8");

        Assertions.assertEquals(humanMouseGeneRelationResponses.get(0).getHumanGene().getSymbol(), "Aoc3");
        Assertions.assertEquals(humanMouseGeneRelationResponses.get(0).getMouseGene().getSymbol(), "Fgf8");
    }

    @Test
    @DisplayName("Should throw Exception if There is no Relation with given identifier")
    void retrieveHumanMouseGeneRelationByIdentifier_ThrowException() {

        //WHEN
        Exception exception = Assertions.assertThrows(NoRelationFoundException.class, () -> orthologService.retrieveHumanMouseGeneRelationByIdentifier("MGI:99604"));
        String actualMessage = exception.getMessage();

        //THEN
        Assertions.assertTrue(actualMessage.contains(Constant.NO_RELATION_FOUND_FOR_GIVEN_INPUT));

    }

    @Test
    @DisplayName("Should return Correct HumanMouseGeneRelationResponse if Relation found with given identifier")
    void retrieveHumanMouseGeneRelationByIdentifier_ReturnRelation() throws NoRelationFoundException {
        //WHEN
        Mockito.when(orthologRepository.findByMouseGeneMgiGeneAccId(Mockito.anyString())).thenReturn(TestData.dummyOrthologList());

        List<HumanMouseGeneRelationResponse> humanMouseGeneRelationResponses = orthologService.retrieveHumanMouseGeneRelationByIdentifier("MGI:99604");

        Assertions.assertEquals(humanMouseGeneRelationResponses.get(0).getHumanGene().getSymbol(), "Aoc3");
        Assertions.assertEquals(humanMouseGeneRelationResponses.get(0).getMouseGene().getSymbol(), "Fgf8");
    }

    @Test
    @DisplayName("Should throw Exception if There is no multiple human genes for given symbol")
    void retrieveHumanGenesByMouseGeneSymbol_ThrowException() {
        //WHEN
        Exception exception = Assertions.assertThrows(HumanGeneNotFoundException.class, () ->
                orthologService.retrieveHumanGenesByMouseGeneSymbol("Fgf8"));
        String actualMessage = exception.getMessage();

        //THEN
        Assertions.assertTrue(actualMessage.contains(Constant.NO_HUMAN_GENE_MATCHED_WITH_GIVEN_SYMBOL));
    }

    @Test
    @DisplayName("Should return correct HumanGenesResponse if There is multiple human genes for given symbol")
    void retrieveHumanGenesByMouseGeneSymbol_ReturnHumanGenesResponse() throws HumanGeneNotFoundException {
        //WHEN
        Mockito.when(orthologRepository.findByMouseGeneSymbol(Mockito.anyString())).thenReturn(TestData.dummyOrthologList());

        List<MultipleHumanGenesResponse> multipleHumanGenesResponseList = orthologService.retrieveHumanGenesByMouseGeneSymbol("Fgf8");

        Assertions.assertEquals(multipleHumanGenesResponseList.get(0).getHumanGene().getSymbol(), "Aoc3");
        Assertions.assertEquals(multipleHumanGenesResponseList.get(0).getSupportCount(), 3L);
    }
}
