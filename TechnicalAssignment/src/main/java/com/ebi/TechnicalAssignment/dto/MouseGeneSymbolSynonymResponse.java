package com.ebi.TechnicalAssignment.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class MouseGeneSymbolSynonymResponse {

    private String symbol;
    private String synonym;
}
