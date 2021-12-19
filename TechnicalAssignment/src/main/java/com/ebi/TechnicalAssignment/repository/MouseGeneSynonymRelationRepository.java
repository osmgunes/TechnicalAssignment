package com.ebi.TechnicalAssignment.repository;

import com.ebi.TechnicalAssignment.model.mouse.MouseGeneSynonymRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface MouseGeneSynonymRelationRepository extends JpaRepository<MouseGeneSynonymRelation, Long> {

    List<MouseGeneSynonymRelation> findByMouseGeneSymbol(String symbol);

    List<MouseGeneSynonymRelation> findByMouseGeneSynonymSynonym(String synonym);

}
