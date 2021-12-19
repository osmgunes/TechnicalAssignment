package com.ebi.TechnicalAssignment.model.mouse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mouse_gene_synonym")
@Data
public class MouseGeneSynonym {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "mgi_gene_acc_id")
    private String mgiGeneAccId;
    @Column(name = "synonym")
    private String synonym;

    @JsonIgnore
    @OneToMany()
    @JoinColumn(name = "id")
    List<MouseGeneSynonymRelation> mouseGeneSynonymRelation = new ArrayList();
}
