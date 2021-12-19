package com.ebi.TechnicalAssignment.model.mouse;

import com.ebi.TechnicalAssignment.model.ortholog.Ortholog;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mouse_gene")
@Data
public class MouseGene {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "ensembl_chromosome")
    private String ensemblChromosome;
    @Column(name = "ensembl_gene_acc_id")
    private String ensemblGeneAccId;
    @Column(name = "ensembl_start")
    private Long ensemblStart;
    @Column(name = "ensembl_stop")
    private Long ensemblStop;
    @Column(name = "ensembl_strand")
    private String ensemblStrand;
    @Column(name = "entrez_gene_acc_id")
    private Long entrez_gene_acc_id;
    @Column(name = "genome_build")
    private String genomeBuild;
    @Column(name = "mgi_gene_acc_id")
    private String mgiGeneAccId;
    @Column(name = "name")
    private String name;
    @Column(name = "mgi_cm")
    private String mgiCm;
    @Column(name = "mgi_chromosome")
    private String mgiChromosome;
    @Column(name = "mgi_start")
    private Long mgiStart;
    @Column(name = "mgi_stop")
    private Long mgiStop;
    @Column(name = "mgi_strand")
    private String mgiStrand;
    @Column(name = "ncbi_chromosome")
    private String ncbiChromosome;
    @Column(name = "ncbi_start")
    private Long ncbiStart;
    @Column(name = "ncbi_stop")
    private Long ncbiStop;
    @Column(name = "ncbi_strand")
    private String ncbiStrand;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "type")
    private String type;
    @Column(name = "subtype")
    private String subtype;

    @JsonIgnore
    @OneToMany()
    @JoinColumn(name = "id")
    List<Ortholog> ortholog = new ArrayList();

    @JsonIgnore
    @OneToMany()
    @JoinColumn(name = "id")
    List<MouseGeneSynonymRelation> mouseGeneSynonymRelation = new ArrayList();

}
