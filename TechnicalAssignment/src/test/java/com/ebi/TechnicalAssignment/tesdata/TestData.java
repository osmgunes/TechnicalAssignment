package com.ebi.TechnicalAssignment.tesdata;

import com.ebi.TechnicalAssignment.dto.HumanMouseGeneRelationResponse;
import com.ebi.TechnicalAssignment.model.human.HumanGene;
import com.ebi.TechnicalAssignment.model.mouse.MouseGene;
import com.ebi.TechnicalAssignment.model.mouse.MouseGeneSynonym;
import com.ebi.TechnicalAssignment.model.mouse.MouseGeneSynonymRelation;
import com.ebi.TechnicalAssignment.model.ortholog.Ortholog;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static MouseGene dummyMouseGene() {
        MouseGene mouseGene = new MouseGene();
        mouseGene.setId(2305L);
        mouseGene.setSymbol("Fgf8");
        mouseGene.setMgiGeneAccId("MGI:99604");
        return mouseGene;
    }

    public static HumanGene dummyHumanGene() {
        HumanGene humanGene = new HumanGene();
        humanGene.setId(29L);
        humanGene.setSymbol("Aoc3");
        return humanGene;
    }

    public static MouseGeneSynonym dummyMouseGeneSynonym() {
        MouseGeneSynonym mouseGeneSynonym = new MouseGeneSynonym();
        mouseGeneSynonym.setId(39045L);
        mouseGeneSynonym.setMgiGeneAccId("MGI:99604");
        mouseGeneSynonym.setSynonym("Aigf");
        return mouseGeneSynonym;
    }

    public static List<MouseGeneSynonymRelation> dummyMouseGeneSynonymRelationList() {
        List<MouseGeneSynonymRelation> mouseGeneSynonymRelationList = new ArrayList<>();
        MouseGeneSynonymRelation mouseGeneSynonymRelation = new MouseGeneSynonymRelation();
        mouseGeneSynonymRelation.setMouseGene(TestData.dummyMouseGene());
        mouseGeneSynonymRelation.setMouseGeneSynonym(TestData.dummyMouseGeneSynonym());
        mouseGeneSynonymRelationList.add(mouseGeneSynonymRelation);
        return mouseGeneSynonymRelationList;
    }

    public static List<Ortholog> dummyOrthologList() {
        List<Ortholog> orthologList = new ArrayList<>();
        Ortholog ortholog = new Ortholog();
        ortholog.setHumanGene(dummyHumanGene());
        ortholog.setMouseGene(dummyMouseGene());
        ortholog.setSupportCount(3L);
        orthologList.add(ortholog);

        return orthologList;
    }

}
