package com.ebi.TechnicalAssignment.model.human;


import com.ebi.TechnicalAssignment.model.ortholog.Ortholog;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "human_gene")
@Data
public class HumanGene {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "hgnc_acc_id")
    private String hgncAccId;
    @Column(name = "name")
    private String name;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "ensembl_gene_acc_id ")
    private String ensemblGeneAccId;
    @Column(name = "entrez_gene_acc_id")
    private Long entrezGeneAccId;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    List<Ortholog> ortholog = new ArrayList();

}
