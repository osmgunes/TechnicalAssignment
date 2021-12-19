package com.ebi.TechnicalAssignment.model.ortholog;

import com.ebi.TechnicalAssignment.model.human.HumanGene;
import com.ebi.TechnicalAssignment.model.mouse.MouseGene;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "ortholog")
@Data
public class Ortholog {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "support")
    private String support;
    @Column(name = "support_raw")
    private String supportRaw;
    @Column(name = "support_count")
    private Long supportCount;
    @Column(name = "category")
    private String category;
    @Column(name = "is_max_human_to_mouse")
    private String isMaxHumanToMouse;
    @Column(name = "is_max_mouse_to_human")
    private String isMaxMouseToHuman;

    @ManyToOne
    @JoinColumn(name = "human_gene_id")
    private HumanGene humanGene;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "mouse_gene_id")
    private MouseGene mouseGene;
}
