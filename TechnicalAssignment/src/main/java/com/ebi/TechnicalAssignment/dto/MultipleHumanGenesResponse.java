package com.ebi.TechnicalAssignment.dto;


import com.ebi.TechnicalAssignment.model.human.HumanGene;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MultipleHumanGenesResponse {

    HumanGene humanGene;
    Long supportCount;

}
