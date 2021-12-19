package com.ebi.TechnicalAssignment.dto;


import com.ebi.TechnicalAssignment.model.human.HumanGene;
import com.ebi.TechnicalAssignment.model.mouse.MouseGene;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Builder
@Value
@Setter
public class HumanMouseGeneRelationResponse {

    private HumanGene humanGene;
    private MouseGene mouseGene;
}
