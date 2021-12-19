package com.ebi.TechnicalAssignment.model.mouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "mouse_gene_synonym_relation")
@Data
public class MouseGeneSynonymRelation {

    @Id
    @Column(name = "ctid")
    String id;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "mouse_gene_id")
    private MouseGene mouseGene;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "mouse_gene_synonym_id")
    private MouseGeneSynonym mouseGeneSynonym;


}
